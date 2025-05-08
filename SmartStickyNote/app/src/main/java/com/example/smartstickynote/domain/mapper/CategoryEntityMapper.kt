package com.example.smartstickynote.domain.mapper

import com.example.smartstickynote.data.local.entity.CategoryEntity
import com.example.smartstickynote.domain.model.Category

fun CategoryEntity.toDomain(): Category = Category(id, title, color, updatedAt)

fun Category.toEntity(): CategoryEntity = CategoryEntity(id, title, color, updatedAt)
