package com.example.smartstickynote.domain.usecase

import com.example.smartstickynote.domain.model.Filter
import com.example.smartstickynote.domain.repository.NoteRepository
import com.example.smartstickynote.domain.model.Note
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class AddNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.addNote(note)
}

//class GetNotesUseCase @Inject constructor(private val repository: NoteRepository) {
//    operator fun invoke(): Flow<List<Note>> = repository.getNotes()
//}

class GetNotesUseCase @Inject constructor(
    private val repository: NoteRepository
) {
    operator fun invoke(filter: Filter): Flow<List<Note>> {
        return repository.getNotes(filter)
    }
}


class DeleteNoteUseCase @Inject constructor(private val repository: NoteRepository) {
    suspend operator fun invoke(note: Note) = repository.deleteNote(note)
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
        // Chuyển trạng thái isFavorite của ghi chú
        noteRepository.updateNote(note.copy(isFavorite = !note.isFavorite))
    }
}
