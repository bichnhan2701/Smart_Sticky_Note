package com.example.smartstickynote.domain.repository

import com.example.smartstickynote.domain.model.Folder
import com.example.smartstickynote.domain.model.Note
import kotlinx.coroutines.flow.Flow

interface FolderRepository {
    suspend fun addFolder(folder: Folder)
    suspend fun deleteFolder(folder: Folder)
    fun getFolderById(id: String): Flow<Folder?>
    suspend fun updateFolder(folder: Folder)
    fun getAllFolders(): Flow<List<Folder>>
    fun getNotesByFolder(folderId: String): Flow<List<Note>>
    fun getNotesWithoutFolder(): Flow<List<Note>>
    suspend fun moveNoteToFolder(noteId: String, folderId: String?)
    fun getFoldersWithCount(): Flow<List<Folder>>
    
    // Đồng bộ hóa
    suspend fun syncFoldersToCloud(userId: String)
    suspend fun fetchFoldersFromCloud(userId: String)
} 