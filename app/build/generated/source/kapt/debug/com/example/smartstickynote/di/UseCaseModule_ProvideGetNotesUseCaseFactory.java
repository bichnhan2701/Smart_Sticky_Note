package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.GetNotesUseCase;
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
public final class UseCaseModule_ProvideGetNotesUseCaseFactory implements Factory<GetNotesUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideGetNotesUseCaseFactory(Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public GetNotesUseCase get() {
    return provideGetNotesUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideGetNotesUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideGetNotesUseCaseFactory(repositoryProvider);
  }

  public static GetNotesUseCase provideGetNotesUseCase(NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideGetNotesUseCase(repository));
  }
}
