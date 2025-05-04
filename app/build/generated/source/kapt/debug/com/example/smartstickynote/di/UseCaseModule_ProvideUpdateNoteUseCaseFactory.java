package com.example.smartstickynote.di;

import com.example.smartstickynote.domain.repository.NoteRepository;
import com.example.smartstickynote.domain.usecase.UpdateNoteUseCase;
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
public final class UseCaseModule_ProvideUpdateNoteUseCaseFactory implements Factory<UpdateNoteUseCase> {
  private final Provider<NoteRepository> repositoryProvider;

  public UseCaseModule_ProvideUpdateNoteUseCaseFactory(
      Provider<NoteRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public UpdateNoteUseCase get() {
    return provideUpdateNoteUseCase(repositoryProvider.get());
  }

  public static UseCaseModule_ProvideUpdateNoteUseCaseFactory create(
      Provider<NoteRepository> repositoryProvider) {
    return new UseCaseModule_ProvideUpdateNoteUseCaseFactory(repositoryProvider);
  }

  public static UpdateNoteUseCase provideUpdateNoteUseCase(NoteRepository repository) {
    return Preconditions.checkNotNullFromProvides(UseCaseModule.INSTANCE.provideUpdateNoteUseCase(repository));
  }
}
