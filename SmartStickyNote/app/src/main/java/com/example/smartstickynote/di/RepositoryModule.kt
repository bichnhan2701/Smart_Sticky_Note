package com.example.smartstickynote.di

import com.example.smartstickynote.data.repository.CategoryRepositoryImpl
import com.example.smartstickynote.data.repository.NoteRepositoryImpl
import com.example.smartstickynote.domain.repository.CategoryRepository
import com.example.smartstickynote.domain.repository.NoteRepository
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
    abstract fun bindCategoryRepository(
        impl: CategoryRepositoryImpl
    ): CategoryRepository

}
