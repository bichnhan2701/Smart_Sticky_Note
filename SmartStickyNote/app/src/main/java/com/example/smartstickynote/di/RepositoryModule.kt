package com.example.smartstickynote.di

import com.example.smartstickynote.data.repository.FolderRepositoryImpl
import com.example.smartstickynote.data.repository.NoteRepositoryImpl
import com.example.smartstickynote.data.repository.TagRepositoryImpl
import com.example.smartstickynote.domain.repository.FolderRepository
import com.example.smartstickynote.domain.repository.NoteRepository
import com.example.smartstickynote.domain.repository.TagRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindNoteRepository(
        impl: NoteRepositoryImpl
    ): NoteRepository
    
    @Binds
    @Singleton
    abstract fun bindFolderRepository(
        impl: FolderRepositoryImpl
    ): FolderRepository
    
    @Binds
    @Singleton
    abstract fun bindTagRepository(
        impl: TagRepositoryImpl
    ): TagRepository
}
