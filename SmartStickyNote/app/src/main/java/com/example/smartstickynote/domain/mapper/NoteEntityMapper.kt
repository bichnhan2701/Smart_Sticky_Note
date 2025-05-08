package com.example.smartstickynote.domain.mapper

import com.example.smartstickynote.data.local.entity.NoteEntity
import com.example.smartstickynote.domain.model.Note

fun NoteEntity.toDomain(): Note = Note(
    id = id,
    title = title,
    content = content,
    priorityRate = priorityRate,
    isFavorite = isFavorite,
    isPin = isPin,
    createdAt = createdAt,
    updatedAt = updatedAt,
    categoryId = categoryId
)

fun Note.toEntity(): NoteEntity = NoteEntity(
    id = id,
    title = title,
    content = content,
    priorityRate = priorityRate,
    isFavorite = isFavorite,
    isPin = isPin,
    createdAt = createdAt,
    updatedAt = updatedAt,
    categoryId = categoryId
)
