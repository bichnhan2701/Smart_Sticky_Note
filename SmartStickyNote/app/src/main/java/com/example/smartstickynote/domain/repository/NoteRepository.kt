package com.example.smartstickynote.domain.repository

import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun addNote(note: Note)
    suspend fun deleteNote(note: Note, userId: String)
    fun getNoteById(id: String): Flow<Note?>
    suspend fun updateNote(note: Note)
    suspend fun toggleFavorite(note: Note)
    suspend fun togglePin(note: Note)
    fun getNotes(filter: Filter): Flow<List<Note>>
    suspend fun getNotesForWidget(): Note?

    suspend fun syncAllNotesToFirebase(userId: String)
    suspend fun fetchAllNotesFromFirebase(userId: String)
}