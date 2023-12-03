package com.example.notesdemo.domain.use_cases

import android.content.Context
import com.example.notesdemo.domain.repository.NotesRepository
import com.example.notesdemo.domain.model.NotesEntity


class AddNotesUseCase constructor(private val notesRepository: NotesRepository) {

    fun addNotes(note: NotesEntity) {
        notesRepository.addNote(note)
    }

    fun getAllNotes(): List<NotesEntity>?  {
        return notesRepository.getAllNotes()
    }
}