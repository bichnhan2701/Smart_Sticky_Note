package com.example.smartstickynote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartstickynote.data.local.dao.FolderDao
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.local.dao.TagDao
import com.example.smartstickynote.data.local.entity.FolderEntity
import com.example.smartstickynote.data.local.entity.NoteEntity
import com.example.smartstickynote.data.local.entity.NoteTagCrossRef
import com.example.smartstickynote.data.local.entity.TagEntity

@Database(
    entities = [
        NoteEntity::class,
        FolderEntity::class,
        TagEntity::class,
        NoteTagCrossRef::class
    ],
    version = 3,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun folderDao(): FolderDao
    abstract fun tagDao(): TagDao
}