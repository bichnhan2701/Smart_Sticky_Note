package com.example.smartstickynote.domain.model

data class Note(
    val id: String,
    val title: String,
    val content: String,
    val priorityRate: String,
    val isFavorite: Boolean,
    val isPin: Boolean,
    val createdAt: Long
)
