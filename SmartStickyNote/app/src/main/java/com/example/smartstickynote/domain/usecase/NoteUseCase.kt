package com.example.smartstickynote.domain.usecase

import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.repository.NoteRepository
import com.example.smartstickynote.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(filter: Filter): Flow<List<Note>> {
        return repository.getNotes(filter)
    }
}

class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    //    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
    suspend operator fun invoke(note: Note, userId: String) {
        repository.deleteNote(note, userId)
    }
}

class GetNoteByIdUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(id: String): Flow<Note?> {
        return repository.getNoteById(id)
    }
}

class UpdateNoteUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        repository.updateNote(note)
    }
}

class ToggleFavoriteNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.toggleFavorite(note)
    }
}

class TogglePinNoteUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(note: Note) {
        noteRepository.togglePin(note)
    }
}

class GetNoteForWidgetUseCase @Inject constructor(
    private val noteRepository: NoteRepository
) {
    suspend operator fun invoke(): Note? {
        return noteRepository.getNotesForWidget()
    }
}

class SyncNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    suspend fun uploadLocalToFirebase(userId: String) {
        repository.syncAllNotesToFirebase(userId)
    }

    suspend fun downloadFirebaseToLocal(userId: String) {
        repository.fetchAllNotesFromFirebase(userId)
    }
}
