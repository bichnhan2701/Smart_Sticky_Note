package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.DeleteNoteUseCase;
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
public final class UseCaseModule_ProvideDeleteNoteUseCaseFactory implements Factory<DeleteNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideDeleteNoteUseCaseFactory(
      Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public DeleteNoteUseCase get() {
    return provideDeleteNoteUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideDeleteNoteUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideDeleteNoteUseCaseFactory(repositoryProvider);
  }

  public static DeleteNoteUseCase provideDeleteNoteUseCase(NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideDeleteNoteUseCase(repository));
  }
}
