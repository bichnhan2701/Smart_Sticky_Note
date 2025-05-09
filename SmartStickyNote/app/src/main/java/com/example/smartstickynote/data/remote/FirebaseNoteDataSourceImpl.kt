package com.example.smartstickynote.data.remote

import android.util.Log
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.model.Note
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import javax.inject.Inject
import kotlin.coroutines.resume
import kotlin.coroutines.resumeWithException
import kotlin.coroutines.suspendCoroutine

class FirebaseNoteDataSourceImpl @Inject constructor(
    private val database: DatabaseReference
) : FirebaseNoteDataSource {

    override suspend fun uploadNote(note: Note, userId: String) {
        Log.d("SYNC", "Uploading note: ${note.id} for user $userId")
        try {
            database.child("notes").child(userId).child("notes").child("note ${note.id}").setValue(note)
                .addOnSuccessListener {
                    Log.d("SYNC", "Note uploaded successfully: ${note.id}")
                }
                .addOnFailureListener { error ->
                    Log.e("SYNC", "Failed to upload note: ${note.id}", error)
                }
        } catch (exception: Exception) {
            Log.e("SYNC", "Exception occurred while uploading note: ${note.id}", exception)
        }
    }

    override suspend fun deleteNote(noteId: String, userId: String) {
        try {
            database.child("notes").child(userId).child("notes").child("note $noteId").removeValue()
                .addOnSuccessListener {
                    Log.d("SYNC", "Note deleted successfully: $noteId")
                }
                .addOnFailureListener { error ->
                    Log.e("SYNC", "Failed to delete note: $noteId", error)
                }
        } catch (exception: Exception) {
            Log.e("SYNC", "Exception occurred while deleting note: $noteId", exception)
        }
    }

    override suspend fun getAllNotes(userId: String): List<Note> = suspendCoroutine { cont ->
        database.child("notes").child(userId).child("notes")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notes = snapshot.children.mapNotNull { it.getValue(Note::class.java) }
                    cont.resume(notes)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("SYNC", "Error fetching notes: ${error.message}", error.toException())
                    cont.resumeWithException(error.toException())
                }
            })
    }

    override suspend fun uploadCategory(category: Category, userId: String) {
        try {
            database.child("notes").child(userId).child("categories").child(category.id).setValue(category)
                .addOnSuccessListener {
                    Log.d("SYNC", "Category uploaded successfully: ${category.id}")
                }
                .addOnFailureListener { error ->
                    Log.e("SYNC", "Failed to upload category: ${category.id}", error)
                }
        } catch (exception: Exception) {
            Log.e("SYNC", "Exception occurred while uploading category: ${category.id}", exception)
        }
    }

    override suspend fun deleteCategory(categoryId: String, userId: String) {
        try {
            database.child("notes").child(userId).child("categories").child(categoryId).removeValue()
                .addOnSuccessListener {
                    Log.d("SYNC", "Category deleted successfully: $categoryId")
                }
                .addOnFailureListener { error ->
                    Log.e("SYNC", "Failed to delete category: $categoryId", error)
                }
        } catch (exception: Exception) {
            Log.e("SYNC", "Exception occurred while deleting category: $categoryId", exception)
        }
    }

    override suspend fun getAllCategories(userId: String): List<Category> = suspendCoroutine { cont ->
        database.child("notes").child(userId).child("categories")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val categories = snapshot.children.mapNotNull { it.getValue(Category::class.java) }
                    cont.resume(categories)
                }

                override fun onCancelled(error: DatabaseError) {
                    Log.e("SYNC", "Error fetching categories: ${error.message}", error.toException())
                    cont.resumeWithException(error.toException())
                }
            })
    }
}


