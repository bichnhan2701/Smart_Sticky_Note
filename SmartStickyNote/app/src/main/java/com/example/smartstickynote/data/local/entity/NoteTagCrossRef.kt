package com.example.smartstickynote.data.local.entity

import androidx.room.Entity

@Entity(primaryKeys = ["noteId", "tagId"], tableName = "note_tag_cross_ref")
data class NoteTagCrossRef(
    val noteId: String,
    val tagId: String
) 