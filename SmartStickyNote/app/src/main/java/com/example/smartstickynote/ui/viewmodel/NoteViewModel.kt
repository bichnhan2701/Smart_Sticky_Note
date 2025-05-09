package com.example.smartstickynote.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.usecase.*
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
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
    private val auth: FirebaseAuth,
    private val getNotesByCategoryUseCase: GetNotesByCategoryUseCase,
    private val getAllCategoriesUseCase: GetAllCategoriesUseCase
) : ViewModel() {

    private val _filter = MutableStateFlow(Filter.getDefaultFilter())
    val setFilter: StateFlow<Filter> = _filter

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories: StateFlow<List<Category>> = _categories.asStateFlow()

    // Dùng combine để kết hợp bộ lọc, tìm kiếm và danh mục
    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = combine(
        _filter,
        _searchQuery
    ) { filter, query ->
        Pair(filter, query)
    }.flatMapLatest { (filter, query) ->
        val notesFlow = when (filter) {
            is Filter.CATEGORY -> getNotesByCategoryUseCase(filter.categoryId)
            else -> getNotesUseCase(filter)
        }

        notesFlow.map { notes ->
            if (query.isBlank()) notes
            else notes.filter {
                it.title.contains(query, ignoreCase = true) ||
                        it.content.contains(query, ignoreCase = true)
            }
        }
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val notes: StateFlow<List<Note>> = _notes

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

    fun setFilter(filter: Filter) {
        _filter.value = filter
    }

    // Cập nhật danh mục được chọn
    fun setCategoryFilter(categoryId: String?) {
        _filter.value = if (categoryId != null) {
            Filter.CATEGORY(categoryId)
        } else {
            Filter.NONE
        }
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note)
//            syncNotes()
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
            updateNoteUseCase(note)
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

    fun clearAllLocalNotes() {
        viewModelScope.launch {
            deleteNoteUseCase.deleteAllLocalNotes()
        }
    }


    init {
        loadCategories()
    }

    private fun loadCategories() {
        viewModelScope.launch {
            getAllCategoriesUseCase().collect {
                _categories.value = it
            }
        }
    }

    fun testInsertNoteWithInvalidCategory() {
        viewModelScope.launch {
            val invalidNote = Note(
                id = "test-id",
                title = "Note lỗi",
                content = "Sẽ crash nếu categoryId không tồn tại",
                priorityRate = "Medium",
                isFavorite = false,
                isPin = false,
                createdAt = System.currentTimeMillis(),
                updatedAt = System.currentTimeMillis(),
                categoryId = "non-existent-id"
            )
            addNote(invalidNote)
        }
    }


}