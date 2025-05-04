package com.example.smartstickynote.data.repository

import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.domain.mapper.toEntity
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.repository.NoteRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao
) : NoteRepository {
    override suspend fun addNote(note: Note) {
        dao.insertNote(note.toEntity())
    }

//    override fun getNotes(): Flow<List<Note>> {
//        return dao.getAllNotes().map { list -> list.map { it.toDomain() } }
//    }

    override suspend fun deleteNote(note: Note) {
        dao.deleteNote(note.toEntity())
    }

    override fun getNoteById(id: String): Flow<Note?> {
        return dao.getNoteById(id).map { it?.toDomain() }
    }

    override suspend fun updateNote(note: Note) {
        dao.updateNote(note.toEntity())
    }

    override suspend fun toggleFavorite(note: Note) {
        val updated = note.copy(isFavorite = !note.isFavorite)
        dao.updateNote(updated.toEntity())
    }

    override fun getNotes(filter: Filter): Flow<List<Note>> {
        return when (filter) {
            Filter.HIGH -> dao.getNotesByPriority("High")
            Filter.MEDIUM -> dao.getNotesByPriority("Medium")
            Filter.LOW -> dao.getNotesByPriority("Low")
            Filter.FAVORITE -> dao.getFavoriteNotes()
            Filter.NONE -> dao.getAllNotes()
        }.map { it.map { entity -> entity.toDomain() } }
    }
}
