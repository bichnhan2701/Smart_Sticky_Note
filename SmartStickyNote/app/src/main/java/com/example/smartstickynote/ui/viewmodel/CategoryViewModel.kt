package com.example.smartstickynote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Category
import com.example.smartstickynote.domain.usecase.AddCategoryUseCase
import com.example.smartstickynote.domain.usecase.DeleteCategoryUseCase
import com.example.smartstickynote.domain.usecase.GetAllCategoriesUseCase
import com.example.smartstickynote.domain.usecase.GetCategoryByIdUseCase
import com.example.smartstickynote.domain.usecase.SyncCategoriesUseCase
import com.example.smartstickynote.domain.usecase.UpdateCategoryUseCase
import com.google.firebase.auth.FirebaseAuth
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CategoryViewModel @Inject constructor(
    getAllCategoriesUseCase: GetAllCategoriesUseCase,
    private val addCategoryUseCase: AddCategoryUseCase,
    private val deleteCategoryUseCase: DeleteCategoryUseCase,
    private val getCategoryByIdUseCase: GetCategoryByIdUseCase,
    private val updateCategoryUseCase: UpdateCategoryUseCase,
    private val syncCategoriesUseCase: SyncCategoriesUseCase,
    private val auth: FirebaseAuth
) : ViewModel() {


    val categories = getAllCategoriesUseCase().stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(5000),
        initialValue = emptyList()
    )

    fun addCategory(name: String, color: Int) {
        viewModelScope.launch {
            addCategoryUseCase(Category(title = name, color = color))
            syncCategories()
        }
    }

    fun deleteCategory(category: Category) {
        viewModelScope.launch {
            deleteCategoryUseCase(category, auth.currentUser?.uid ?: return@launch)
            syncCategories()
        }
    }

    fun getCategoryById(categoryId: String): Flow<Category?> {
        return getCategoryByIdUseCase(categoryId)
    }

    fun updateCategory(category: Category) {
        viewModelScope.launch {
            updateCategoryUseCase(category)
            syncCategories()
        }
    }

    private fun syncCategories() {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid ?: return@launch
            syncCategoriesUseCase.uploadLocalToFirebase(userId)
        }
    }

    fun restoreCategories() {
        viewModelScope.launch {
            val userId = auth.currentUser?.uid ?: return@launch
            syncCategoriesUseCase.downloadFirebaseToLocal(userId)
        }
    }

    fun clearAllLocalCategories() {
        viewModelScope.launch {
            deleteCategoryUseCase.deleteAllLocalCategories()
        }
    }
}
