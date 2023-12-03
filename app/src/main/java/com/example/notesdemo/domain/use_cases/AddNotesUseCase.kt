package com.example.notesdemo.domain.use_cases

import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository

class AddNotesUseCase constructor(private val notesRepository: NotesRepository) {

    fun addNotes(note: NotesEntity) {
        notesRepository.addNote(note)
    }

    fun getAllNotes(): List<NotesEntity>? {
        return notesRepository.getAllNotes()
    }
}