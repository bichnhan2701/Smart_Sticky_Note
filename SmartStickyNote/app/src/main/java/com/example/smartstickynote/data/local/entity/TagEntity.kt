package com.example.smartstickynote.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "tags")
data class TagEntity(
    @PrimaryKey val id: String,
    val name: String,
    val color: String,
    val createdAt: Long,
    val userId: String? = null // Để hỗ trợ đồng bộ hóa với tài khoản người dùng
) 