package com.example.notesdemo.data.repository

import com.example.notesdemo.data.data_source.db.NotesDao
import com.example.notesapp.notes.domain.model.NotesEntity
import com.example.notesapp.notes.domain.repository.NotesRepository
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(private val noteDao: NotesDao): NotesRepository {
    override suspend fun addNote(notesEntity: NotesEntity) {
        noteDao.addNotes(notesEntity)
    }

    override suspend fun editNote(notesEntity: NotesEntity) {
        noteDao.updateNotes(notesEntity)
    }
}