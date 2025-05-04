package com.example.smartstickynote.ui.viewmodel;

import com.example.smartstickynote.domain.usecase.AddNoteUseCase;
import com.example.smartstickynote.domain.usecase.DeleteNoteUseCase;
import com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase;
import com.example.smartstickynote.domain.usecase.GetNotesUseCase;
import com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase;
import com.example.smartstickynote.domain.usecase.UpdateNoteUseCase;
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
public final class NoteViewModel_Factory implements Factory<NoteViewModel> {
  private final Provider<AddNoteUseCase> addNoteUseCaseProvider;

  private final Provider<GetNotesUseCase> getNotesUseCaseProvider;

  private final Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider;

  private final Provider<GetNoteByIdUseCase> getNoteByIdUseCaseProvider;

  private final Provider<UpdateNoteUseCase> updateNoteUseCaseProvider;

  private final Provider<ToggleFavoriteNoteUseCase> toggleFavoriteNoteUseCaseProvider;

  public NoteViewModel_Factory(Provider<AddNoteUseCase> addNoteUseCaseProvider,
      Provider<GetNotesUseCase> getNotesUseCaseProvider,
      Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider,
      Provider<GetNoteByIdUseCase> getNoteByIdUseCaseProvider,
      Provider<UpdateNoteUseCase> updateNoteUseCaseProvider,
      Provider<ToggleFavoriteNoteUseCase> toggleFavoriteNoteUseCaseProvider) {
    this.addNoteUseCaseProvider = addNoteUseCaseProvider;
    this.getNotesUseCaseProvider = getNotesUseCaseProvider;
    this.deleteNoteUseCaseProvider = deleteNoteUseCaseProvider;
    this.getNoteByIdUseCaseProvider = getNoteByIdUseCaseProvider;
    this.updateNoteUseCaseProvider = updateNoteUseCaseProvider;
    this.toggleFavoriteNoteUseCaseProvider = toggleFavoriteNoteUseCaseProvider;
  }

  @Override
  public NoteViewModel get() {
    return newInstance(addNoteUseCaseProvider.get(), getNotesUseCaseProvider.get(), deleteNoteUseCaseProvider.get(), getNoteByIdUseCaseProvider.get(), updateNoteUseCaseProvider.get(), toggleFavoriteNoteUseCaseProvider.get());
  }

  public static NoteViewModel_Factory create(Provider<AddNoteUseCase> addNoteUseCaseProvider,
      Provider<GetNotesUseCase> getNotesUseCaseProvider,
      Provider<DeleteNoteUseCase> deleteNoteUseCaseProvider,
      Provider<GetNoteByIdUseCase> getNoteByIdUseCaseProvider,
      Provider<UpdateNoteUseCase> updateNoteUseCaseProvider,
      Provider<ToggleFavoriteNoteUseCase> toggleFavoriteNoteUseCaseProvider) {
    return new NoteViewModel_Factory(addNoteUseCaseProvider, getNotesUseCaseProvider, deleteNoteUseCaseProvider, getNoteByIdUseCaseProvider, updateNoteUseCaseProvider, toggleFavoriteNoteUseCaseProvider);
  }

  public static NoteViewModel newInstance(AddNoteUseCase addNoteUseCase,
      GetNotesUseCase getNotesUseCase, DeleteNoteUseCase deleteNoteUseCase,
      GetNoteByIdUseCase getNoteByIdUseCase, UpdateNoteUseCase updateNoteUseCase,
      ToggleFavoriteNoteUseCase toggleFavoriteNoteUseCase) {
    return new NoteViewModel(addNoteUseCase, getNotesUseCase, deleteNoteUseCase, getNoteByIdUseCase, updateNoteUseCase, toggleFavoriteNoteUseCase);
  }
}
