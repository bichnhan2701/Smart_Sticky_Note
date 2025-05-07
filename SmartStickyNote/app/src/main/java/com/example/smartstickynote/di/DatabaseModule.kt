package com.example.smartstickynote.di

import android.content.Context
import androidx.room.Room
import com.example.smartstickynote.data.NoteDatabase
import com.example.smartstickynote.data.local.dao.FolderDao
import com.example.smartstickynote.data.local.dao.NoteDao
import com.example.smartstickynote.data.local.dao.TagDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): NoteDatabase {
        return Room.databaseBuilder(
            context,
            NoteDatabase::class.java,
            "note_db"
        )
            .fallbackToDestructiveMigration(false)
            .build()
    }

    @Provides
    fun provideNoteDao(db: NoteDatabase): NoteDao = db.noteDao()
    
    @Provides
    fun provideTagDao(db: NoteDatabase): TagDao = db.tagDao()
    
    @Provides
    fun provideFolderDao(db: NoteDatabase): FolderDao = db.folderDao()
}
