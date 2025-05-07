package com.example.smartstickynote.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import com.example.smartstickynote.data.local.entity.NoteTagCrossRef
import com.example.smartstickynote.data.local.entity.TagEntity
import com.example.smartstickynote.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface TagDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTag(tag: TagEntity)

    @Query("SELECT * FROM tags ORDER BY name ASC")
    fun getAllTags(): Flow<List<TagEntity>>

    @Delete
    suspend fun deleteTag(tag: TagEntity)

    @Query("SELECT * FROM tags WHERE id = :id")
    fun getTagById(id: String): Flow<TagEntity?>

    @Update
    suspend fun updateTag(tag: TagEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addTagToNote(crossRef: NoteTagCrossRef)

    @Delete
    suspend fun removeTagFromNote(crossRef: NoteTagCrossRef)

    @Query("DELETE FROM note_tag_cross_ref WHERE tagId = :tagId")
    suspend fun removeAllNoteReferencesForTag(tagId: String)

    @Query("DELETE FROM note_tag_cross_ref WHERE noteId = :noteId")
    suspend fun removeAllTagsForNote(noteId: String)

    @Transaction
    @Query("SELECT * FROM tags JOIN note_tag_cross_ref ON tags.id = note_tag_cross_ref.tagId WHERE note_tag_cross_ref.noteId = :noteId")
    fun getTagsForNote(noteId: String): Flow<List<TagEntity>>

    @Transaction
    @Query("SELECT * FROM notes JOIN note_tag_cross_ref ON notes.id = note_tag_cross_ref.noteId WHERE note_tag_cross_ref.tagId = :tagId")
    fun getNotesWithTag(tagId: String): Flow<List<NoteEntity>>

    @Query("SELECT COUNT(*) FROM note_tag_cross_ref WHERE tagId = :tagId")
    fun getNotesCountWithTag(tagId: String): Flow<Int>

    @Query("SELECT * FROM tags WHERE userId = :userId OR userId IS NULL ORDER BY name ASC")
    fun getTagsForUser(userId: String?): Flow<List<TagEntity>>
} 