package com.example.smartstickynote.domain.model

data class Folder(
    val id: String = "",
    val name: String = "",
    val color: String = "#9C55FF", // Màu mặc định
    val createdAt: Long = 0L,
    val updatedAt: Long = 0L,
    val userId: String? = null,
    val noteCount: Int = 0 // Số lượng ghi chú trong thư mục
) 