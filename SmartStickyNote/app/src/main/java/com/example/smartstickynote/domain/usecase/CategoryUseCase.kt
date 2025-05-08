package com.example.smartstickynote.domain.usecase

import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.repository.CategoryRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddCategoryUseCase @Inject constructor(private val repo: CategoryRepository) {
    suspend operator fun invoke(category: Category) = repo.addCategory(category)
}

class DeleteCategoryUseCase @Inject constructor(private val repo: CategoryRepository) {
    suspend operator fun invoke(category: Category, userId: String) = repo.deleteCategory(category, userId)

    suspend fun deleteAllLocalCategories() {
        repo.clearAllLocalCategories()
    }
}

class GetAllCategoriesUseCase @Inject constructor(private val repo: CategoryRepository) {
    operator fun invoke(): Flow<List<Category>> = repo.getAllCategories()
}

class GetCategoryByIdUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    operator fun invoke(categoryId: String): Flow<Category?> {
        return repository.getCategoryById(categoryId)
    }
}

class UpdateCategoryUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend operator fun invoke(category: Category) {
        repository.updateCategory(category)
    }
}

class SyncCategoriesUseCase @Inject constructor(
    private val repository: CategoryRepository
) {
    suspend fun uploadLocalToFirebase(userId: String) {
        repository.syncAllCategoriesToFirebase(userId)
    }

    suspend fun downloadFirebaseToLocal(userId: String) {
        repository.fetchAllCategoriesFromFirebase(userId)
    }
}