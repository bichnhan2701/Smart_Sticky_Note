package com.example.smartstickynote.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.domain.repository.TagRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class TagViewModel @Inject constructor(
    private val tagRepository: TagRepository
) : ViewModel() {

    // Lấy danh sách thẻ (kèm số lượng ghi chú)
    val tags: Flow<List<Tag>> = tagRepository.getTagsWithCount()

    // Thêm thẻ mới
    fun addTag(tag: Tag) {
        viewModelScope.launch {
            tagRepository.addTag(tag)
        }
    }

    // Cập nhật thẻ
    fun updateTag(tag: Tag) {
        viewModelScope.launch {
            tagRepository.updateTag(tag)
        }
    }

    // Xóa thẻ
    fun deleteTag(tag: Tag) {
        viewModelScope.launch {
            tagRepository.deleteTag(tag)
        }
    }

    // Thêm thẻ cho ghi chú
    fun addTagToNote(noteId: String, tagId: String) {
        viewModelScope.launch {
            tagRepository.addTagToNote(noteId, tagId)
        }
    }

    // Xóa thẻ khỏi ghi chú
    fun removeTagFromNote(noteId: String, tagId: String) {
        viewModelScope.launch {
            tagRepository.removeTagFromNote(noteId, tagId)
        }
    }
    
    // Lấy tất cả thẻ của một ghi chú
    fun getTagsForNote(noteId: String): Flow<List<Tag>> {
        return tagRepository.getTagsForNote(noteId)
    }
} 