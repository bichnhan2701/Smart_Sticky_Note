package com.example.smartstickynote.domain.repository

import com.example.smartstickynote.domain.model.Category
import kotlinx.coroutines.flow.Flow

interface CategoryRepository {
    suspend fun addCategory(category: Category)
    suspend fun deleteCategory(category: Category, userId: String)
    fun getAllCategories(): Flow<List<Category>>
    fun getCategoryById(categoryId: String): Flow<Category?>
    suspend fun updateCategory(category: Category)

    suspend fun syncAllCategoriesToFirebase(userId: String)
    suspend fun fetchAllCategoriesFromFirebase(userId: String)

    suspend fun clearAllLocalCategories()
}
