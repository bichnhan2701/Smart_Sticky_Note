package com.example.smartstickynote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.usecase.AddNoteUseCase
import com.example.smartstickynote.domain.usecase.DeleteNoteUseCase
import com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase
import com.example.smartstickynote.domain.usecase.GetNotesUseCase
import com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase
import com.example.smartstickynote.domain.usecase.UpdateNoteUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
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
) : ViewModel() {

    private val _filter = MutableStateFlow(Filter.NONE)
    val setFilter: StateFlow<Filter> = _filter

    @OptIn(ExperimentalCoroutinesApi::class)
    private val _notes = _filter.flatMapLatest { filter ->
        getNotesUseCase(filter)
    }.stateIn(viewModelScope, SharingStarted.WhileSubscribed(5000), emptyList())

    val notes: StateFlow<List<Note>> = _notes

    fun setFilter(filter: Filter) {
        _filter.value = filter
    }

    fun addNote(note: Note) {
        viewModelScope.launch {
            addNoteUseCase(note)
        }
    }

    fun deleteNote(note: Note) {
        viewModelScope.launch {
            deleteNoteUseCase(note)
        }
    }

    fun getNoteById(id: String): Flow<Note?> {
        return getNoteByIdUseCase(id)
    }

    fun updateNote(note: Note) {
        viewModelScope.launch {
            updateNoteUseCase(note)
        }
    }

    fun toggleFavorite(note: Note) {
        viewModelScope.launch {
            toggleFavoriteNoteUseCase(note)
        }
    }
}