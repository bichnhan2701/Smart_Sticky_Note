package com.example.smartstickynote.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u000f\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0018\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\b0\f2\u0006\u0010\r\u001a\u00020\u000eH\u0016J\u001c\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00100\f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0016\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tJ\u0016\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0096@\u00a2\u0006\u0002\u0010\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/example/smartstickynote/data/repository/NoteRepositoryImpl;", "Lcom/example/smartstickynote/domain/repository/NoteRepository;", "dao", "Lcom/example/smartstickynote/data/local/dao/NoteDao;", "(Lcom/example/smartstickynote/data/local/dao/NoteDao;)V", "addNote", "", "note", "Lcom/example/smartstickynote/domain/model/Note;", "(Lcom/example/smartstickynote/domain/model/Note;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteNote", "getNoteById", "Lkotlinx/coroutines/flow/Flow;", "id", "", "getNotes", "", "filter", "Lcom/example/smartstickynote/domain/model/Filter;", "toggleFavorite", "updateNote", "app_debug"})
public final class NoteRepositoryImpl implements com.example.smartstickynote.domain.repository.NoteRepository {
    @org.jetbrains.annotations.NotNull()
    private final com.example.smartstickynote.data.local.dao.NoteDao dao = null;
    
    @javax.inject.Inject()
    public NoteRepositoryImpl(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.data.local.dao.NoteDao dao) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object addNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.smartstickynote.domain.model.Note> getNoteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Note note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartstickynote.domain.model.Note>> getNotes(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.domain.model.Filter filter) {
        return null;
    }
}