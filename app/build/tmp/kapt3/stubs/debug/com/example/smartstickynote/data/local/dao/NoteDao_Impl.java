package com.example.smartstickynote.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0007\u0018\u0000 \u001a2\u00020\u0001:\u0001\u001aB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u0016\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0014\u0010\u000f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u0010H\u0016J\u0014\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u0010H\u0016J\u0018\u0010\u0013\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u00102\u0006\u0010\u0014\u001a\u00020\u0015H\u0016J\u001c\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00110\u00102\u0006\u0010\u0017\u001a\u00020\u0015H\u0016J\u0016\u0010\u0018\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000eJ\u0016\u0010\u0019\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u0007H\u0096@\u00a2\u0006\u0002\u0010\u000eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00070\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001b"}, d2 = {"Lcom/example/smartstickynote/data/local/dao/NoteDao_Impl;", "Lcom/example/smartstickynote/data/local/dao/NoteDao;", "__db", "Landroidx/room/RoomDatabase;", "(Landroidx/room/RoomDatabase;)V", "__deleteAdapterOfNoteEntity", "Landroidx/room/EntityDeleteOrUpdateAdapter;", "Lcom/example/smartstickynote/data/local/entity/NoteEntity;", "__insertAdapterOfNoteEntity", "Landroidx/room/EntityInsertAdapter;", "__updateAdapterOfNoteEntity", "deleteNote", "", "note", "(Lcom/example/smartstickynote/data/local/entity/NoteEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getAllNotes", "Lkotlinx/coroutines/flow/Flow;", "", "getFavoriteNotes", "getNoteById", "id", "", "getNotesByPriority", "priority", "insertNote", "updateNote", "Companion", "app_debug"})
@javax.annotation.processing.Generated(value = {"androidx.room.RoomProcessor"})
@kotlin.Suppress(names = {"UNCHECKED_CAST", "DEPRECATION", "REDUNDANT_PROJECTION", "REMOVAL"})
public final class NoteDao_Impl implements com.example.smartstickynote.data.local.dao.NoteDao {
    @org.jetbrains.annotations.NotNull()
    private final androidx.room.RoomDatabase __db = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.room.EntityInsertAdapter<com.example.smartstickynote.data.local.entity.NoteEntity> __insertAdapterOfNoteEntity = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.room.EntityDeleteOrUpdateAdapter<com.example.smartstickynote.data.local.entity.NoteEntity> __deleteAdapterOfNoteEntity = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.room.EntityDeleteOrUpdateAdapter<com.example.smartstickynote.data.local.entity.NoteEntity> __updateAdapterOfNoteEntity = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.example.smartstickynote.data.local.dao.NoteDao_Impl.Companion Companion = null;
    
    public NoteDao_Impl(@org.jetbrains.annotations.NotNull()
    androidx.room.RoomDatabase __db) {
        super();
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object insertNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.data.local.entity.NoteEntity note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object deleteNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.data.local.entity.NoteEntity note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.Nullable()
    public java.lang.Object updateNote(@org.jetbrains.annotations.NotNull()
    com.example.smartstickynote.data.local.entity.NoteEntity note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartstickynote.data.local.entity.NoteEntity>> getAllNotes() {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<com.example.smartstickynote.data.local.entity.NoteEntity> getNoteById(@org.jetbrains.annotations.NotNull()
    java.lang.String id) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartstickynote.data.local.entity.NoteEntity>> getNotesByPriority(@org.jetbrains.annotations.NotNull()
    java.lang.String priority) {
        return null;
    }
    
    @java.lang.Override()
    @org.jetbrains.annotations.NotNull()
    public kotlinx.coroutines.flow.Flow<java.util.List<com.example.smartstickynote.data.local.entity.NoteEntity>> getFavoriteNotes() {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0003\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00050\u0004\u00a8\u0006\u0006"}, d2 = {"Lcom/example/smartstickynote/data/local/dao/NoteDao_Impl$Companion;", "", "()V", "getRequiredConverters", "", "Lkotlin/reflect/KClass;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<kotlin.reflect.KClass<?>> getRequiredConverters() {
            return null;
        }
    }
}