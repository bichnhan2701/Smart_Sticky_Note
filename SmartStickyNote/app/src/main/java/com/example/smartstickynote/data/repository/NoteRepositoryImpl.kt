package com.example.smartstickynote.data.repository

import android.util.Log
import com.example.smartstickynote.data.local.dao.CategoryDao
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.remote.FirebaseNoteDataSource
import com.example.smartstickynote.domain.mapper.toEntity
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.repository.NoteRepository
import com.google.firebase.auth.FirebaseAuth
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val categoryDao: CategoryDao,
    private val firebase: FirebaseNoteDataSource
) : NoteRepository {

    // Helper function to validate categoryId
    private suspend fun sanitizeCategoryId(categoryId: String?): String? {
        val exists = categoryId != null && categoryDao.getCategoryByIdOnce(categoryId) != null
        Log.d("SANITIZE", "CategoryId $categoryId exists: $exists")
        return if (exists) categoryId else null
    }

    override suspend fun addNote(note: Note) {
        val validCategoryId = sanitizeCategoryId(note.categoryId) // Kiểm tra categoryId có hợp lệ không
        val updatedNote = note.copy(categoryId = validCategoryId) // Cập nhật ghi chú với categoryId hợp lệ
        dao.insertNote(updatedNote.toEntity())

        val userId = FirebaseAuth.getInstance().currentUser?.uid
        if (userId != null) {
            firebase.uploadNote(updatedNote, userId) // Upload Firebase
        }
    }


    override suspend fun deleteNote(note: Note, userId: String) {
        dao.deleteNote(note.toEntity())
        firebase.deleteNote(note.id, userId)
    }

    override fun getNoteById(id: String): Flow<Note?> {
        return dao.getNoteById(id).map { it?.toDomain() }
    }

    override suspend fun updateNote(note: Note) {
        // Sanitize categoryId before updating note
        val sanitizedCategoryId = sanitizeCategoryId(note.categoryId)
        val updatedNote = note.copy(categoryId = sanitizedCategoryId, updatedAt = System.currentTimeMillis())

        dao.updateNote(updatedNote.toEntity())
    }

    override suspend fun toggleFavorite(note: Note) {
        val updated = note.copy(isFavorite = !note.isFavorite, updatedAt = System.currentTimeMillis())
        dao.updateNote(updated.toEntity())
    }

    override suspend fun togglePin(note: Note) {
        val update = note.copy(isPin = !note.isPin, updatedAt = System.currentTimeMillis())
        dao.updateNote(update.toEntity())
    }

    override fun getNotes(filter: Filter): Flow<List<Note>> {
        return when (filter) {
            is Filter.HIGH -> dao.getNotesByPriority("Cao")
            is Filter.MEDIUM -> dao.getNotesByPriority("Trung bình")
            is Filter.LOW -> dao.getNotesByPriority("Thấp")
            is Filter.FAVORITE -> dao.getFavoriteNotes()
            is Filter.NONE -> dao.getAllNotes()
            is Filter.CATEGORY -> dao.getNotesByCategory(filter.categoryId) // categoryId có thể là NULL
        }.map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun getNotesForWidget(): Note? {
        return dao.getPinNote()?.toDomain()
    }

    override suspend fun syncAllNotesToFirebase(userId: String) {
        val notes = dao.getAllNotesOnce()
        Log.d("SYNC", "Syncing ${notes.size} notes to Firebase")
        notes.forEach { note ->
            Log.d("SYNC", "Syncing note ${note.id} with title ${note.title}")
            firebase.uploadNote(note.toDomain(), userId)
        }
    }

    override suspend fun fetchAllNotesFromFirebase(userId: String) {
        val notesFromCloud = firebase.getAllNotes(userId)
        notesFromCloud.forEach { note ->
            val localNote = dao.getNoteByIdOnce(note.id)?.toDomain()
            if (localNote == null || note.updatedAt > localNote.updatedAt) {
                val sanitizedCategoryId = sanitizeCategoryId(note.categoryId)
                Log.d("SYNC", "Sanitized categoryId for note '${note.title}': $sanitizedCategoryId")
                val updatedNote = note.copy(categoryId = sanitizedCategoryId)
                dao.insertNote(updatedNote.toEntity())
            }
        }
    }

    override fun getNotesByCategory(categoryId: String?): Flow<List<Note>> {
        return dao.getNotesByCategory(categoryId).map { it.map { entity -> entity.toDomain() } }
    }

    override suspend fun clearAllLocalNotes() {
        dao.clearAllNotes()
    }
}