package com.example.smartstickynote.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "notes")
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val priorityRate: String, // "High", "Medium", "Low"
    val isFavorite: Boolean,
    val isPin: Boolean,
    val createdAt: Long,
    val updatedAt: Long
)
