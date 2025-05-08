package com.example.smartstickynote.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = CategoryEntity::class,
            parentColumns = ["id"],
            childColumns = ["categoryId"],
            onDelete = ForeignKey.SET_NULL // Cho phép NULL khi xóa danh mục
        )
    ]
)
data class NoteEntity(
    @PrimaryKey val id: String,
    val title: String,
    val content: String,
    val priorityRate: String, // "High", "Medium", "Low"
    val isFavorite: Boolean,
    val isPin: Boolean,
    val createdAt: Long,
    val updatedAt: Long,
    val categoryId: String? // Chấp nhận NULL nếu không có danh mục
)
