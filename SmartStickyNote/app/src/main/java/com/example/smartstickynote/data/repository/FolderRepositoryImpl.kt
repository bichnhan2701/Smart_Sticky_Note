package com.example.smartstickynote.data.repository

import com.example.smartstickynote.data.local.dao.FolderDao
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.mapper.toEntity
import com.example.smartstickynote.domain.model.Folder
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.repository.FolderRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FolderRepositoryImpl @Inject constructor(
    private val folderDao: FolderDao,
    private val noteDao: NoteDao
) : FolderRepository {

    override suspend fun addFolder(folder: Folder) {
        folderDao.insertFolder(
            com.example.smartstickynote.data.local.entity.FolderEntity(
                id = folder.id,
                name = folder.name,
                color = folder.color,
                createdAt = folder.createdAt,
                updatedAt = folder.updatedAt,
                userId = folder.userId
            )
        )
    }

    override suspend fun deleteFolder(folder: Folder) {
        // Trước khi xóa thư mục, cần di chuyển các ghi chú ra khỏi thư mục
        folderDao.removeNotesFromFolder(folder.id)
        folderDao.deleteFolder(
            com.example.smartstickynote.data.local.entity.FolderEntity(
                id = folder.id,
                name = folder.name,
                color = folder.color,
                createdAt = folder.createdAt,
                updatedAt = folder.updatedAt,
                userId = folder.userId
            )
        )
    }

    override fun getFolderById(id: String): Flow<Folder?> {
        val folderFlow = folderDao.getFolderById(id)
        val countFlow = folderDao.getNotesCountInFolder(id)

        return combine(folderFlow, countFlow) { folder, count ->
            folder?.let {
                Folder(
                    id = it.id,
                    name = it.name,
                    color = it.color,
                    createdAt = it.createdAt,
                    updatedAt = it.updatedAt,
                    userId = it.userId,
                    noteCount = count
                )
            }
        }
    }

    override suspend fun updateFolder(folder: Folder) {
        folderDao.updateFolder(
            com.example.smartstickynote.data.local.entity.FolderEntity(
                id = folder.id,
                name = folder.name,
                color = folder.color,
                createdAt = folder.createdAt,
                updatedAt = System.currentTimeMillis(),
                userId = folder.userId
            )
        )
    }

    override fun getAllFolders(): Flow<List<Folder>> {
        return folderDao.getAllFolders().map { entities ->
            entities.map { entity ->
                Folder(
                    id = entity.id,
                    name = entity.name,
                    color = entity.color,
                    createdAt = entity.createdAt,
                    updatedAt = entity.updatedAt,
                    userId = entity.userId
                )
            }
        }
    }

    override fun getFoldersWithCount(): Flow<List<Folder>> {
        // Sử dụng phương thức getFoldersWithNotesCount() mới
        return folderDao.getFoldersWithNotesCount().map { folderWithNotes ->
            folderWithNotes.map { folderWithCount ->
                Folder(
                    id = folderWithCount.id,
                    name = folderWithCount.name,
                    color = folderWithCount.color,
                    createdAt = folderWithCount.createdAt,
                    updatedAt = folderWithCount.updatedAt,
                    userId = folderWithCount.userId,
                    noteCount = folderWithCount.noteCount
                )
            }
        }
    }

    override fun getNotesByFolder(folderId: String): Flow<List<Note>> {
        return noteDao.getNotesByFolder(folderId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getNotesWithoutFolder(): Flow<List<Note>> {
        return noteDao.getNotesWithoutFolder().map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override suspend fun moveNoteToFolder(noteId: String, folderId: String?) {
        noteDao.moveNoteToFolder(noteId, folderId)
    }

    override suspend fun syncFoldersToCloud(userId: String) {
        // Sẽ triển khai sau khi có Firebase service
    }

    override suspend fun fetchFoldersFromCloud(userId: String) {
        // Sẽ triển khai sau khi có Firebase service
    }
} 