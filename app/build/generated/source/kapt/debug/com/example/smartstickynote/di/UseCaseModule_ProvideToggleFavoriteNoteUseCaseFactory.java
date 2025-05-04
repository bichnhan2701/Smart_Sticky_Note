package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class UseCaseModule_ProvideToggleFavoriteNoteUseCaseFactory implements Factory<ToggleFavoriteNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideToggleFavoriteNoteUseCaseFactory(
      Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ToggleFavoriteNoteUseCase get() {
    return provideToggleFavoriteNoteUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideToggleFavoriteNoteUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideToggleFavoriteNoteUseCaseFactory(repositoryProvider);
  }

  public static ToggleFavoriteNoteUseCase provideToggleFavoriteNoteUseCase(
      NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideToggleFavoriteNoteUseCase(repository));
  }
}
