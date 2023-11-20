package com.example.notesapp.notes.domain.repository

import com.example.notesapp.notes.domain.model.NotesEntity

interface NotesRepository {

    suspend fun addNote(notesEntity: NotesEntity)

    suspend fun editNote(notesEntity: NotesEntity)

}