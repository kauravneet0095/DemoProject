package com.example.notesdemo.data.repository

import android.content.Context
import com.example.notesdemo.domain.repository.NotesRepository
import com.example.notesdemo.data.data_source.db.NotesDatabase
import com.example.notesdemo.domain.model.NotesEntity

class NotesRepositoryImpl constructor(
    private val notesDatabase: NotesDatabase?
) : NotesRepository {

    override fun addNote(notesEntity: NotesEntity) {
        notesDatabase?.notesDao?.addNotes(notesEntity)
    }

    override fun getAllNotes(): List<NotesEntity>? {
        return notesDatabase?.notesDao?.getAllNotes()
    }


}