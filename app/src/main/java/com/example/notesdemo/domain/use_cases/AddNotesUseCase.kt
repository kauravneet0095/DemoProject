package com.example.notesdemo.domain.use_cases

import android.content.Context
import com.example.notesapp.notes.domain.repository.NotesRepository
import com.example.notesdemo.domain.model.NotesEntity
import javax.inject.Inject


class AddNotesUseCase @Inject constructor(private val notesRepository: NotesRepository) {

    suspend fun addNotes(note: NotesEntity) {
        notesRepository.addNote(note)
    }

    suspend fun editNotes(note: NotesEntity) {
        notesRepository.editNote(note)
    }

    suspend fun getAllNotes(context: Context): List<NotesEntity> {
        return notesRepository.getAllNotes(context)

    }
}