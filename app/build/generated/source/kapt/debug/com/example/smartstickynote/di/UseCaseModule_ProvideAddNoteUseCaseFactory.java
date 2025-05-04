package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.AddNoteUseCase;
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
public final class UseCaseModule_ProvideAddNoteUseCaseFactory implements Factory<AddNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideAddNoteUseCaseFactory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AddNoteUseCase get() {
    return provideAddNoteUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideAddNoteUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideAddNoteUseCaseFactory(repositoryProvider);
  }

  public static AddNoteUseCase provideAddNoteUseCase(NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideAddNoteUseCase(repository));
  }
}
