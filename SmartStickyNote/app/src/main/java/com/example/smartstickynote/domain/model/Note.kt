package com.example.smartstickynote.domain.model

data class Note(
    var id: String = "",
    var title: String = "",
    var content: String = "",
    var priorityRate: String = "",
    var isFavorite: Boolean = false,
    var isPin: Boolean = false,
    var createdAt: Long = 0L,
    var updatedAt: Long = 0L,
    var categoryId: String? = ""
)