package com.example.smartstickynote.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
    private val auth: FirebaseAuth
) : ViewModel() {

    private val _filter = MutableStateFlow(Filter.NONE)
    val setFilter: StateFlow<Filter> = _filter

    private val _searchQuery = MutableStateFlow("")
    val searchQuery: StateFlow<String> = _searchQuery

    fun setSearchQuery(query: String) {
        _searchQuery.value = query
    }

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
            addNoteUseCase(note)
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
}