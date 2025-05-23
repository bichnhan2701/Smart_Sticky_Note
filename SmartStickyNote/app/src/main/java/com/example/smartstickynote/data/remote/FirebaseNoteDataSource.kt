package com.example.smartstickynote.data.remote

import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.model.Note

interface FirebaseNoteDataSource {
    suspend fun uploadNote(note: Note, userId: String)
    suspend fun deleteNote(noteId: String, userId: String)
    suspend fun getAllNotes(userId: String): List<Note>

    suspend fun uploadCategory(category: Category, userId: String)
    suspend fun deleteCategory(categoryId: String, userId: String)
    suspend fun getAllCategories(userId: String): List<Category>
}
