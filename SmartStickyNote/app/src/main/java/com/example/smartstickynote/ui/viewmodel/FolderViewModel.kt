package com.example.smartstickynote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Folder
import com.example.smartstickynote.domain.repository.FolderRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FolderViewModel @Inject constructor(
    private val folderRepository: FolderRepository
) : ViewModel() {

    // Lấy danh sách thư mục (kèm số lượng ghi chú)
    val folders: Flow<List<Folder>> = folderRepository.getFoldersWithCount()

    // Thêm thư mục mới
    fun addFolder(folder: Folder) {
        viewModelScope.launch {
            folderRepository.addFolder(folder)
        }
    }

    // Cập nhật thư mục
    fun updateFolder(folder: Folder) {
        viewModelScope.launch {
            folderRepository.updateFolder(folder)
        }
    }

    // Xóa thư mục
    fun deleteFolder(folder: Folder) {
        viewModelScope.launch {
            folderRepository.deleteFolder(folder)
        }
    }

    // Di chuyển ghi chú vào một thư mục
    fun moveNoteToFolder(noteId: String, folderId: String?) {
        viewModelScope.launch {
            folderRepository.moveNoteToFolder(noteId, folderId)
        }
    }
} 