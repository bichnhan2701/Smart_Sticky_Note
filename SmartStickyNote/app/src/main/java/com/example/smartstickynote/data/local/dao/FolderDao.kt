package com.example.smartstickynote.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.smartstickynote.data.local.entity.FolderEntity
import kotlinx.coroutines.flow.Flow

/**
 * Đại diện cho thông tin thư mục với số lượng ghi chú
 */
data class FolderWithCount(
    val folder: FolderEntity,
    val count: Int
)

@Dao
interface FolderDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertFolder(folder: FolderEntity)

    @Query("SELECT * FROM folders ORDER BY name ASC")
    fun getAllFolders(): Flow<List<FolderEntity>>

    @Delete
    suspend fun deleteFolder(folder: FolderEntity)

    @Query("SELECT * FROM folders WHERE id = :id")
    fun getFolderById(id: String): Flow<FolderEntity?>

    @Query("UPDATE notes SET folderId = NULL WHERE folderId = :folderId")
    suspend fun removeNotesFromFolder(folderId: String)

    @Update
    suspend fun updateFolder(folder: FolderEntity)

    @Query("SELECT COUNT(*) FROM notes WHERE folderId = :folderId")
    fun getNotesCountInFolder(folderId: String): Flow<Int>

    @Query("SELECT * FROM folders WHERE userId = :userId OR userId IS NULL ORDER BY name ASC")
    fun getFoldersForUser(userId: String?): Flow<List<FolderEntity>>
    
    /**
     * Phương thức này được thêm để hỗ trợ FolderRepositoryImpl
     * Trả về danh sách các FolderWithCount, mỗi mục chứa thông tin thư mục và số lượng ghi chú
     */
    @Query("SELECT f.*, COUNT(n.id) as noteCount FROM folders f LEFT JOIN notes n ON f.id = n.folderId GROUP BY f.id")
    fun getFoldersWithNotesCount(): Flow<List<FolderWithNoteCount>>
}

/**
 * Class này được Room sử dụng để ánh xạ truy vấn JOIN giữa thư mục và số lượng ghi chú
 */
data class FolderWithNoteCount(
    val id: String,
    val name: String,
    val color: String,
    val createdAt: Long,
    val updatedAt: Long,
    val userId: String?,
    val noteCount: Int
) 