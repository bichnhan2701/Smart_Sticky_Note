package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase;
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
public final class UseCaseModule_ProvideGetNoteByIdUseCaseFactory implements Factory<GetNoteByIdUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideGetNoteByIdUseCaseFactory(
      Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetNoteByIdUseCase get() {
    return provideGetNoteByIdUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideGetNoteByIdUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideGetNoteByIdUseCaseFactory(repositoryProvider);
  }

  public static GetNoteByIdUseCase provideGetNoteByIdUseCase(NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetNoteByIdUseCase(repository));
  }
}
