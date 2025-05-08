package com.example.smartstickynote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartstickynote.data.local.dao.CategoryDao
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.local.entity.CategoryEntity
import com.example.smartstickynote.data.local.entity.NoteEntity

@Database(
    entities = [
        NoteEntity::class,
        CategoryEntity::class
    ],
    version = 7,
    exportSchema = false
)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
    abstract fun categoryDao(): CategoryDao
}