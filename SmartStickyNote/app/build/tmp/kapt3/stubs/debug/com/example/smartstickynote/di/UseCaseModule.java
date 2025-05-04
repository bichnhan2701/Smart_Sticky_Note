package com.example.smartstickynote.di;

@dagger.Module()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u0006H\u0007J\u0010\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0005\u001a\u00020\u0006H\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/example/smartstickynote/di/UseCaseModule;", "", "()V", "provideAddNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/AddNoteUseCase;", "repository", "Lcom/example/smartstickynote/domain/repository/NoteRepository;", "provideDeleteNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/DeleteNoteUseCase;", "provideGetNoteByIdUseCase", "Lcom/example/smartstickynote/domain/usecase/GetNoteByIdUseCase;", "provideGetNotesUseCase", "Lcom/example/smartstickynote/domain/usecase/GetNotesUseCase;", "provideToggleFavoriteNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/ToggleFavoriteNoteUseCase;", "provideUpdateNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/UpdateNoteUseCase;", "app_debug"})
@dagger.hilt.InstallIn(value = {dagger.hilt.components.SingletonComponent.class})
public final class UseCaseModule {
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartstickynote.di.UseCaseModule INSTANCE = null;
    
    private UseCaseModule() {
        super();
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.AddNoteUseCase provideAddNoteUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.GetNotesUseCase provideGetNotesUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.DeleteNoteUseCase provideDeleteNoteUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase provideGetNoteByIdUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.UpdateNoteUseCase provideUpdateNoteUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
    
    @dagger.Provides()
    @javax.inject.Singleton()
    @org.jetbrains.annotations.NotNull()
    public final com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase provideToggleFavoriteNoteUseCase(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.repository.NoteRepository repository) {
        return null;
    }
}