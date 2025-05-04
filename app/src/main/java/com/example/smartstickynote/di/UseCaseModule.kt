package com.example.smartstickynote.di

import com.example.smartstickynote.domain.repository.AuthRepository
import com.example.smartstickynote.domain.repository.NoteRepository
import com.example.smartstickynote.domain.usecase.AddNoteUseCase
import com.example.smartstickynote.domain.usecase.DeleteNoteUseCase
import com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase
import com.example.smartstickynote.domain.usecase.GetNotesUseCase
import com.example.smartstickynote.domain.usecase.SignInWithGoogleUseCase
import com.example.smartstickynote.domain.usecase.SignOutUseCase
import com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase
import com.example.smartstickynote.domain.usecase.UpdateNoteUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {
    // NoteUseCase
    @Provides
    @Singleton
    fun provideAddNoteUseCase(repository: NoteRepository): AddNoteUseCase {
        return AddNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetNotesUseCase(repository: NoteRepository): GetNotesUseCase {
        return GetNotesUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideDeleteNoteUseCase(repository: NoteRepository): DeleteNoteUseCase {
        return DeleteNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideGetNoteByIdUseCase(repository: NoteRepository): GetNoteByIdUseCase {
        return GetNoteByIdUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideUpdateNoteUseCase(repository: NoteRepository): UpdateNoteUseCase {
        return UpdateNoteUseCase(repository)
    }

    @Provides
    @Singleton
    fun provideToggleFavoriteNoteUseCase(repository: NoteRepository): ToggleFavoriteNoteUseCase {
        return ToggleFavoriteNoteUseCase(repository)
    }

    //UserUseCase
    @Provides
    @Singleton
    fun provideSignInWithGoogleUseCase(
        authRepository: AuthRepository
    ): SignInWithGoogleUseCase {
        return SignInWithGoogleUseCase(authRepository)
    }

    @Provides
    @Singleton
    fun provideSignOutUseCase(
        authRepository: AuthRepository
    ): SignOutUseCase {
        return SignOutUseCase(authRepository)
    }
}