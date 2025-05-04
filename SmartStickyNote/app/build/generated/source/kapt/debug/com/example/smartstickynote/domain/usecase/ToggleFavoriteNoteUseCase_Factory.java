package com.example.smartstickynote.domain.usecase;

import com.example.smartstickynote.domain.repository.NoteRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class ToggleFavoriteNoteUseCase_Factory implements Factory<ToggleFavoriteNoteUseCase> {
  private final Provider<NoteRepository> noteRepositoryProvider;

  public ToggleFavoriteNoteUseCase_Factory(Provider<NoteRepository> noteRepositoryProvider) {
    this.noteRepositoryProvider = noteRepositoryProvider;
  }

  @Override
  public ToggleFavoriteNoteUseCase get() {
    return newInstance(noteRepositoryProvider.get());
  }

  public static ToggleFavoriteNoteUseCase_Factory create(
      Provider<NoteRepository> noteRepositoryProvider) {
    return new ToggleFavoriteNoteUseCase_Factory(noteRepositoryProvider);
  }

  public static ToggleFavoriteNoteUseCase newInstance(NoteRepository noteRepository) {
    return new ToggleFavoriteNoteUseCase(noteRepository);
  }
}
