package com.example.smartstickynote.domain.repository

import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import kotlinx.coroutines.flow.Flow

interface TagRepository {
    suspend fun addTag(tag: Tag)
    suspend fun deleteTag(tag: Tag)
    fun getTagById(id: String): Flow<Tag?>
    suspend fun updateTag(tag: Tag)
    fun getAllTags(): Flow<List<Tag>>
    suspend fun addTagToNote(noteId: String, tagId: String)
    suspend fun removeTagFromNote(noteId: String, tagId: String)
    fun getTagsForNote(noteId: String): Flow<List<Tag>>
    fun getNotesWithTag(tagId: String): Flow<List<Note>>
    fun getTagsWithCount(): Flow<List<Tag>>
    
    // Đồng bộ hóa
    suspend fun syncTagsToCloud(userId: String)
    suspend fun fetchTagsFromCloud(userId: String)
} 