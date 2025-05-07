package com.example.smartstickynote.data.local.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey

@Entity(
    tableName = "notes",
    foreignKeys = [
        ForeignKey(
            entity = FolderEntity::class,
            parentColumns = ["id"],
            childColumns = ["folderId"],
            onDelete = ForeignKey.SET_NULL
        )
    ],
    indices = [Index("folderId")]
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
    val folderId: String? = null,
    val autoCategories: String? = null, // Danh sách các danh mục được tự động gợi ý, lưu dưới dạng JSON
    val userId: String? = null // Để hỗ trợ đồng bộ hóa với tài khoản người dùng
)
