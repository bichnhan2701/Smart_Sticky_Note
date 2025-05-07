package com.example.smartstickynote.data.repository

import com.example.smartstickynote.data.local.dao.TagDao
import com.example.smartstickynote.data.local.entity.NoteTagCrossRef
import com.example.smartstickynote.data.local.entity.TagEntity
import com.example.smartstickynote.domain.mapper.toDomain
import com.example.smartstickynote.domain.model.Note
import com.example.smartstickynote.domain.model.Tag
import com.example.smartstickynote.domain.repository.TagRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class TagRepositoryImpl @Inject constructor(
    private val tagDao: TagDao
) : TagRepository {

    override suspend fun addTag(tag: Tag) {
        tagDao.insertTag(
            TagEntity(
                id = tag.id,
                name = tag.name,
                color = tag.color,
                createdAt = tag.createdAt,
                userId = tag.userId
            )
        )
    }

    override suspend fun deleteTag(tag: Tag) {
        // Trước khi xóa thẻ, cần xóa tất cả các liên kết với ghi chú
        tagDao.removeAllNoteReferencesForTag(tag.id)
        tagDao.deleteTag(
            TagEntity(
                id = tag.id,
                name = tag.name,
                color = tag.color,
                createdAt = tag.createdAt,
                userId = tag.userId
            )
        )
    }

    override fun getTagById(id: String): Flow<Tag?> {
        val tagFlow = tagDao.getTagById(id)
        val countFlow = tagDao.getNotesCountWithTag(id)

        return combine(tagFlow, countFlow) { tag, count ->
            tag?.let {
                Tag(
                    id = it.id,
                    name = it.name,
                    color = it.color,
                    createdAt = it.createdAt,
                    userId = it.userId,
                    noteCount = count
                )
            }
        }
    }

    override suspend fun updateTag(tag: Tag) {
        tagDao.updateTag(
            TagEntity(
                id = tag.id,
                name = tag.name,
                color = tag.color,
                createdAt = tag.createdAt,
                userId = tag.userId
            )
        )
    }

    override fun getAllTags(): Flow<List<Tag>> {
        return tagDao.getAllTags().map { entities ->
            entities.map { entity ->
                Tag(
                    id = entity.id,
                    name = entity.name,
                    color = entity.color,
                    createdAt = entity.createdAt,
                    userId = entity.userId
                )
            }
        }
    }

    override suspend fun addTagToNote(noteId: String, tagId: String) {
        tagDao.addTagToNote(NoteTagCrossRef(noteId = noteId, tagId = tagId))
    }

    override suspend fun removeTagFromNote(noteId: String, tagId: String) {
        tagDao.removeTagFromNote(NoteTagCrossRef(noteId = noteId, tagId = tagId))
    }

    override fun getTagsForNote(noteId: String): Flow<List<Tag>> {
        return tagDao.getTagsForNote(noteId).map { entities ->
            entities.map { entity ->
                Tag(
                    id = entity.id,
                    name = entity.name,
                    color = entity.color,
                    createdAt = entity.createdAt,
                    userId = entity.userId
                )
            }
        }
    }

    override fun getNotesWithTag(tagId: String): Flow<List<Note>> {
        return tagDao.getNotesWithTag(tagId).map { entities ->
            entities.map { it.toDomain() }
        }
    }

    override fun getTagsWithCount(): Flow<List<Tag>> {
        return tagDao.getAllTags().map { tags ->
            tags.map { tag ->
                val count = tagDao.getNotesCountWithTag(tag.id).first()
                Tag(
                    id = tag.id,
                    name = tag.name,
                    color = tag.color,
                    createdAt = tag.createdAt,
                    userId = tag.userId,
                    noteCount = count
                )
            }
        }
    }

    override suspend fun syncTagsToCloud(userId: String) {
        // Sẽ triển khai sau khi có Firebase service
    }

    override suspend fun fetchTagsFromCloud(userId: String) {
        // Sẽ triển khai sau khi có Firebase service
    }
} 