package com.example.notesdemo.data.repository

import android.content.Context
import com.example.notesapp.notes.domain.repository.NotesRepository
import com.example.notesdemo.data.data_source.db.NotesDatabase
import com.example.notesdemo.domain.model.NotesEntity
import javax.inject.Inject

class NotesRepositoryImpl @Inject constructor(
    private var notesDatabase: NotesDatabase
) : NotesRepository {
    private fun initialiseDB(context: Context): NotesDatabase? {
        return NotesDatabase.getInstance(context)
    }

    override suspend fun addNote(notesEntity: NotesEntity) {
        notesDatabase.notesDao.addNotes(notesEntity)
    }

    override suspend fun editNote(notesEntity: NotesEntity) {
        notesDatabase.notesDao.updateNotes(notesEntity)
    }

    override fun getAllNotes(context: Context): List<NotesEntity> {
        notesDatabase = initialiseDB(context)!!
        return notesDatabase.notesDao.getAllNotes(context)
    }

}