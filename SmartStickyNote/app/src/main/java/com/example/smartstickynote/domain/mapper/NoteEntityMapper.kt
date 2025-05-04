package com.example.smartstickynote.domain.mapper

import com.example.smartstickynote.data.local.entity.NoteEntity
import com.example.smartstickynote.domain.model.Note

fun NoteEntity.toDomain(): Note = Note(id, title, content, priorityRate, isFavorite, isPin, createdAt)
fun Note.toEntity(): NoteEntity = NoteEntity(id, title, content, priorityRate, isFavorite, isPin, createdAt)
