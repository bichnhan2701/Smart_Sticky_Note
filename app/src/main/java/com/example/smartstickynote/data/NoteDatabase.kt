package com.example.smartstickynote.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.local.entity.NoteEntity

@Database(entities = [NoteEntity::class], version = 1, exportSchema = false)
abstract class NoteDatabase : RoomDatabase() {
    abstract fun noteDao(): NoteDao
}