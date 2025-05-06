package com.example.smartstickynote.data.remote

import android.util.Log
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
        database.child("notes").child(userId).child("note ${note.id}").setValue(note)
    }

    override suspend fun deleteNote(noteId: String, userId: String) {
        database.child("notes").child(userId).child("note $noteId").removeValue()
    }

    override suspend fun getAllNotes(userId: String): List<Note> = suspendCoroutine { cont ->
        database.child("notes").child(userId)
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
}
