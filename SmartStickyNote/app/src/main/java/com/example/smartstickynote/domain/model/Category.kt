package com.example.smartstickynote.domain.model

import java.util.UUID

data class Category(
    val id: String = UUID.randomUUID().toString(),
    val title: String = "",
    val color: Int = 0,
    val updatedAt: Long = 0L
)

