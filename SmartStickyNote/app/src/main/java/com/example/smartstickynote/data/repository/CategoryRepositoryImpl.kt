package com.example.smartstickynote.data.repository

import com.example.smartstickynote.data.local.dao.CategoryDao
import com.example.smartstickynote.data.remote.FirebaseNoteDataSource
import com.example.smartstickynote.domain.mapper.toEntity
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CategoryRepositoryImpl @Inject constructor(
    private val categoryDao: CategoryDao,
    private val firebase: FirebaseNoteDataSource
): CategoryRepository {
    override suspend fun addCategory(category: Category) {
        categoryDao.insertCategory(category.toEntity())
    }

    override suspend fun deleteCategory(category: Category, userId: String) {
        categoryDao.deleteCategory(category.toEntity())
        firebase.deleteCategory(category.id, userId)
    }

    override fun getAllCategories(): Flow<List<Category>> {
        return categoryDao.getAllCategories().map { list -> list.map { it.toDomain() } }
    }

    override fun getCategoryById(categoryId: String): Flow<Category?> {
        return categoryDao.getCategoryById(categoryId).map { it?.toDomain() }
    }

    override suspend fun updateCategory(category: Category) {
        categoryDao.updateCategory(category.toEntity())
    }

    override suspend fun syncAllCategoriesToFirebase(userId: String) {
        val categories = categoryDao.getAllCategoriesOnce()
        categories.forEach { category ->
            firebase.uploadCategory(category.toDomain(), userId)
        }
    }

    override suspend fun fetchAllCategoriesFromFirebase(userId: String) {
        val categoriesFromCloud = firebase.getAllCategories(userId)
        categoriesFromCloud.forEach { category ->
            val localCategory = categoryDao.getCategoryByIdOnce(category.id)?.toDomain()
            if (localCategory == null || category.updatedAt > localCategory.updatedAt) {
                categoryDao.insertCategory(category.toEntity())
            }
        }
    }

    override suspend fun clearAllLocalCategories() {
        categoryDao.clearAllCategories()
    }

}
