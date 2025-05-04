package com.example.smartstickynote.ui.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0007\u0018\u00002\u00020\u0001B7\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u00a2\u0006\u0002\u0010\u000eJ\u000e\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015J\u000e\u0010 \u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015J\u0016\u0010!\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00150\"2\u0006\u0010#\u001a\u00020$J\u000e\u0010\u001b\u001a\u00020\u001e2\u0006\u0010%\u001a\u00020\u0011J\u000e\u0010&\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015J\u000e\u0010\'\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0015R\u0014\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010X\u0082\u0004\u00a2\u0006\u0002\n\u0000R \u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013X\u0082\u0004\u00a2\u0006\b\n\u0000\u0012\u0004\b\u0016\u0010\u0017R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0018\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00110\u0013\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001aR\u000e\u0010\f\u001a\u00020\rX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006("}, d2 = {"Lcom/example/smartstickynote/ui/viewmodel/NoteViewModel;", "Landroidx/lifecycle/ViewModel;", "addNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/AddNoteUseCase;", "getNotesUseCase", "Lcom/example/smartstickynote/domain/usecase/GetNotesUseCase;", "deleteNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/DeleteNoteUseCase;", "getNoteByIdUseCase", "Lcom/example/smartstickynote/domain/usecase/GetNoteByIdUseCase;", "updateNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/UpdateNoteUseCase;", "toggleFavoriteNoteUseCase", "Lcom/example/smartstickynote/domain/usecase/ToggleFavoriteNoteUseCase;", "(Lcom/example/smartstickynote/domain/usecase/AddNoteUseCase;Lcom/example/smartstickynote/domain/usecase/GetNotesUseCase;Lcom/example/smartstickynote/domain/usecase/DeleteNoteUseCase;Lcom/example/smartstickynote/domain/usecase/GetNoteByIdUseCase;Lcom/example/smartstickynote/domain/usecase/UpdateNoteUseCase;Lcom/example/smartstickynote/domain/usecase/ToggleFavoriteNoteUseCase;)V", "_filter", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/example/smartstickynote/domain/model/Filter;", "_notes", "Lkotlinx/coroutines/flow/StateFlow;", "", "Lcom/example/smartstickynote/domain/model/Note;", "get_notes$annotations", "()V", "notes", "getNotes", "()Lkotlinx/coroutines/flow/StateFlow;", "setFilter", "getSetFilter", "addNote", "", "note", "deleteNote", "getNoteById", "Lkotlinx/coroutines/flow/Flow;", "id", "", "filter", "toggleFavorite", "updateNote", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class NoteViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.AddNoteUseCase addNoteUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.GetNotesUseCase getNotesUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.DeleteNoteUseCase deleteNoteUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase getNoteByIdUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.UpdateNoteUseCase updateNoteUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase toggleFavoriteNoteUseCase = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.example.smartstickynote.domain.model.Filter> _filter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.example.smartstickynote.domain.model.Filter> setFilter = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.smartstickynote.domain.model.Note>> _notes = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.smartstickynote.domain.model.Note>> notes = null;
    
    @javax.inject.Inject()
    public NoteViewModel(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.AddNoteUseCase addNoteUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.GetNotesUseCase getNotesUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.DeleteNoteUseCase deleteNoteUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.GetNoteByIdUseCase getNoteByIdUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.UpdateNoteUseCase updateNoteUseCase, @org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.usecase.ToggleFavoriteNoteUseCase toggleFavoriteNoteUseCase) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.example.smartstickynote.domain.model.Filter> getSetFilter() {
        return null;
    }
    
    @kotlin.OptIn(markerClass = {kotlinx.coroutines.ExperimentalCoroutinesApi.class})
    @java.lang.Deprecated()
    private static void get_notes$annotations() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.example.smartstickynote.domain.model.Note>> getNotes() {
        return null;
    }
    
    public final void setFilter(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Filter filter) {
    }
    
    public final void addNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note) {
    }
    
    public final void deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.example.smartstickynote.domain.model.Note> getNoteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    public final void updateNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note) {
    }
}