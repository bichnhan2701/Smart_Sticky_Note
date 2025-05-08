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
        database.child("notes").child(userId).child("notes").child("note ${note.id}").setValue(note)
    }

    override suspend fun deleteNote(noteId: String, userId: String) {
        database.child("notes").child(userId).child("notes").child("note $noteId").removeValue()
    }

    override suspend fun getAllNotes(userId: String): List<Note> = suspendCoroutine { cont ->
        database.child("notes").child(userId).child("notes")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val notes = snapshot.children.mapNotNull { it.getValue(Note::class.java) }
                    cont.resume(notes)
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resumeWithException(error.toException())
                }
            })
    }

    override suspend fun uploadCategory(category: Category, userId: String) {
        database.child("notes").child(userId).child("categories").child(category.id).setValue(category)
    }

    override suspend fun deleteCategory(categoryId: String, userId: String) {
        database.child("notes").child(userId).child("categories").child(categoryId).removeValue()
    }

    override suspend fun getAllCategories(userId: String): List<Category> = suspendCoroutine { cont ->
        database.child("notes").child(userId).child("categories")
            .addListenerForSingleValueEvent(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    val categories = snapshot.children.mapNotNull { it.getValue(Category::class.java) }
                    cont.resume(categories)
                }

                override fun onCancelled(error: DatabaseError) {
                    cont.resumeWithException(error.toException())
                }
            })
    }

}

