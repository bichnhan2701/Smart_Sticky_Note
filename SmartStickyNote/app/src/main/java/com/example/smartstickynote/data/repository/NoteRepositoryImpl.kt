package com.example.smartstickynote.data.repository

import android.util.Log
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.local.dao.TagDao
import com.example.smartstickynote.data.remote.FirebaseNoteDataSource
import com.example.smartstickynote.domain.mapper.toEntity
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.domain.repository.NoteRepository
import com.example.smartstickynote.utils.AutoCategorizer
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class NoteRepositoryImpl @Inject constructor(
    private val dao: NoteDao,
    private val tagDao: TagDao,
    private val firebase: FirebaseNoteDataSource,
    private val autoCategorizer: AutoCategorizer
) : NoteRepository {
    override suspend fun addNote(note: Note) {
        // Trước khi thêm ghi chú, tạo các danh mục tự động
        val categories = autoCategorizer.suggestCategories(note)
        val categoriesJson = autoCategorizer.categoriesToJson(categories)
        
        val noteWithCategories = note.copy(autoCategories = categories)
        val entity = noteWithCategories.toEntity().copy(autoCategories = categoriesJson)
        dao.insertNote(entity)
    }

    override suspend fun deleteNote(note: Note, userId: String) {
        // Xóa tất cả các liên kết với thẻ
        tagDao.removeAllTagsForNote(note.id)
        dao.deleteNote(note.toEntity())
        firebase.deleteNote(note.id, userId)
    }

    override fun getNoteById(id: String): Flow<Note?> {
        return dao.getNoteById(id).map { entity ->
            entity?.let {
                val note = it.toDomain()
                val autoCategories = autoCategorizer.jsonToCategories(it.autoCategories)
                // Không cần chuyển đổi tags ở đây, vì TagRepository sẽ xử lý
                note.copy(autoCategories = autoCategories)
            }
        }
    }

    override suspend fun updateNote(note: Note) {
        // Khi cập nhật ghi chú, cập nhật lại các danh mục tự động
        val categories = autoCategorizer.suggestCategories(note)
        val categoriesJson = autoCategorizer.categoriesToJson(categories)
        
        val noteWithCategories = note.copy(
            autoCategories = categories,
            updatedAt = System.currentTimeMillis()
        )
        val entity = noteWithCategories.toEntity().copy(autoCategories = categoriesJson)
        dao.updateNote(entity)
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
            Filter.HIGH -> dao.getNotesByPriority("High")
            Filter.MEDIUM -> dao.getNotesByPriority("Medium")
            Filter.LOW -> dao.getNotesByPriority("Low")
            Filter.FAVORITE -> dao.getFavoriteNotes()
            Filter.NONE -> dao.getAllNotes()
            Filter.FOLDER, Filter.TAG, Filter.AUTO_CATEGORY -> dao.getAllNotes()
            else -> dao.getAllNotes()
        }.map { entities ->
            entities.map { entity ->
                val note = entity.toDomain()
                val autoCategories = autoCategorizer.jsonToCategories(entity.autoCategories)
                note.copy(autoCategories = autoCategories)
            }
        }
    }

    override suspend fun getNotesForWidget(): Note? {
        return dao.getPinNote()?.toDomain()
    }
    
    override fun searchNotes(query: String): Flow<List<Note>> {
        return dao.searchNotes(query).map { entities ->
            entities.map { entity ->
                val note = entity.toDomain()
                val autoCategories = autoCategorizer.jsonToCategories(entity.autoCategories)
                note.copy(autoCategories = autoCategories)
            }
        }
    }
    
    override fun getNotesByAutoCategory(category: String): Flow<List<Note>> {
        return dao.getNotesByAutoCategory(category).map { entities ->
            entities.map { entity ->
                val note = entity.toDomain()
                val autoCategories = autoCategorizer.jsonToCategories(entity.autoCategories)
                note.copy(autoCategories = autoCategories)
            }
        }
    }
    
    override suspend fun updateAutoCategories(noteId: String, categories: List<String>) {
        val note = dao.getNoteByIdOnce(noteId)
        note?.let {
            val categoriesJson = autoCategorizer.categoriesToJson(categories)
            val updated = it.copy(autoCategories = categoriesJson)
            dao.updateNote(updated)
        }
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
                dao.insertNote(note.toEntity())
            }
        }
    }
}