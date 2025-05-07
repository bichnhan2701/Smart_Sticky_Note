package com.example.smartstickynote.domain.mapper

import com.example.smartstickynote.data.local.entity.NoteEntity
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.utils.AutoCategorizer
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

fun NoteEntity.toDomain(): Note = 
    Note(
        id = id, 
        title = title, 
        content = content, 
        priorityRate = priorityRate, 
        isFavorite = isFavorite, 
        isPin = isPin, 
        createdAt = createdAt, 
        updatedAt = updatedAt,
        folderId = folderId,
        autoCategories = emptyList(), // Sẽ được điền đầy đủ bởi repository
        tags = emptyList(), // Sẽ được điền đầy đủ bởi repository
        userId = userId
    )

fun Note.toEntity(): NoteEntity = 
    NoteEntity(
        id = id, 
        title = title, 
        content = content, 
        priorityRate = priorityRate, 
        isFavorite = isFavorite, 
        isPin = isPin, 
        createdAt = createdAt, 
        updatedAt = updatedAt,
        folderId = folderId,
        autoCategories = null, // Sẽ được điền đầy đủ bởi repository
        userId = userId
    )
