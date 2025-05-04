package com.example.smartstickynote.domain.repository

import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: Note)
//    fun getNotes(): Flow<List<Note>>
    suspend fun deleteNote(note: Note)
    fun getNoteById(id: String): Flow<Note?>
    suspend fun updateNote(note: Note)
    suspend fun toggleFavorite(note: Note)
    fun getNotes(filter: Filter): Flow<List<Note>>
}
