package com.example.smartstickynote.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Folder
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.domain.repository.FolderRepository
import com.example.smartstickynote.domain.repository.TagRepository
import com.example.smartstickynote.domain.usecase.*
import com.example.smartstickynote.utils.EnhancedAutoCategorizer
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NoteViewModel @Inject constructor(
    private val addNoteUseCase: AddNoteUseCase,
    private val getNotesUseCase: GetNotesUseCase,
    private val deleteNoteUseCase: DeleteNoteUseCase,
    private val getNoteByIdUseCase: GetNoteByIdUseCase,
    private val updateNoteUseCase: UpdateNoteUseCase,
    private val toggleFavoriteNoteUseCase: ToggleFavoriteNoteUseCase,
    private val togglePinNoteUseCase: TogglePinNoteUseCase,
    private val syncNotesUseCase: SyncNotesUseCase,
    private val folderRepository: FolderRepository,
    private val tagRepository: TagRepository,
    private val autoCategorizer: EnhancedAutoCategorizer,
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _filter = MutableStateFlow(Filter.NONE)
    val setFilter: StateFlow<Filter> = _filter

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    // Lấy danh sách thư mục
    val folders: Flow<List<Folder>> = folderRepository.getFoldersWithCount()
    
    // Lấy danh sách thẻ
    val tags: Flow<List<Tag>> = tagRepository.getTagsWithCount()

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = combine(_filter, _searchQuery) { filter, query ->
        Pair(filter, query)
    }.flatMapLatest { (filter, query) ->
        getNotesUseCase(filter).map { notes ->
            if (query.isBlank()) notes
            else notes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.content.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val notes: StateFlow<List<Note>> = _notes

    fun setFilter(filter: Filter) {
        _filter.value = filter
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            // Thực hiện phân loại tự động trước khi thêm
            val categories = autoCategorizer.suggestCategories(note)
            val noteWithCategories = note.copy(autoCategories = categories)
            
            // Lưu ghi chú
            addNoteUseCase(noteWithCategories)
            
            // Thêm thẻ cho ghi chú
            noteWithCategories.tags.forEach { tag ->
                tagRepository.addTagToNote(noteWithCategories.id, tag.id)
            }
            
            syncNotes()
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note, auth.currentUser?.uid ?: return@launch)
            syncNotes()
        }
    }

    fun getNoteById(id: String): Flow<Note?> {
        return getNoteByIdUseCase(id)
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            // Thực hiện phân loại tự động khi cập nhật
            val categories = autoCategorizer.suggestCategories(note)
            val noteWithCategories = note.copy(
                autoCategories = categories,
                updatedAt = System.currentTimeMillis()
            )
            
            // Cập nhật ghi chú
            updateNoteUseCase(noteWithCategories)
            
            // Xóa tất cả thẻ hiện tại
            tagRepository.getTagsForNote(note.id).collect { currentTags ->
                currentTags.forEach { tag ->
                    tagRepository.removeTagFromNote(note.id, tag.id)
                }
                
                // Thêm lại các thẻ mới
                noteWithCategories.tags.forEach { tag ->
                    tagRepository.addTagToNote(noteWithCategories.id, tag.id)
                }
            }
            
            syncNotes()
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            toggleFavoriteNoteUseCase(note)
            syncNotes()
        }
    }

    fun togglePin(note: Note) {
        viewModelScope.launch {
            togglePinNoteUseCase(note)
            syncNotes()
        }
    }
    
    // Thêm/xóa thẻ cho ghi chú
    fun addTagToNote(noteId: String, tag: Tag) {
        viewModelScope.launch {
            tagRepository.addTagToNote(noteId, tag.id)
        }
    }
    
    fun removeTagFromNote(noteId: String, tag: Tag) {
        viewModelScope.launch {
            tagRepository.removeTagFromNote(noteId, tag.id)
        }
    }
    
    // Di chuyển ghi chú vào thư mục
    fun moveNoteToFolder(noteId: String, folderId: String?) {
        viewModelScope.launch {
            folderRepository.moveNoteToFolder(noteId, folderId)
        }
    }
    
    // Lấy các thẻ của một ghi chú
    fun getTagsForNote(noteId: String): Flow<List<Tag>> {
        return tagRepository.getTagsForNote(noteId)
    }
    
    // Lấy ghi chú theo thư mục
    fun getNotesByFolder(folderId: String): Flow<List<Note>> {
        return folderRepository.getNotesByFolder(folderId)
    }
    
    // Lấy ghi chú theo thẻ
    fun getNotesWithTag(tagId: String): Flow<List<Note>> {
        return tagRepository.getNotesWithTag(tagId)
    }
    
    // Lấy ghi chú theo danh mục tự động
    fun getNotesByAutoCategory(category: String): Flow<List<Note>> {
        return getNotesUseCase(Filter.NONE).map { notes ->
            notes.filter { note -> 
                note.autoCategories.contains(category)
            }
        }
    }

    private fun syncNotes() {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid ?: return@launch
            Log.d("SYNC", "Current User ID: $userId")
            syncNotesUseCase.uploadLocalToFirebase(userId)
        }
    }

    fun restoreNotes() {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid ?: return@launch
            syncNotesUseCase.downloadFirebaseToLocal(userId)
        }
    }
}