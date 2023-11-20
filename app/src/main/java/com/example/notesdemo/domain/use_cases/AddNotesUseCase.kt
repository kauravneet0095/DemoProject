package com.example.notesdemo.domain.use_cases

import com.example.notesapp.notes.domain.model.NotesEntity
import com.example.notesapp.notes.domain.repository.NotesRepository
import javax.inject.Inject


class AddNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend fun addNotes(note: NotesEntity) {
        notesRepository.addNote(note)
    }

    suspend fun editNotes(note: NotesEntity) {
        notesRepository.editNote(note)
    }
}