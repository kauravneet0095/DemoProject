package com.example.notesdemo.domain.repository

import android.content.Context
import com.example.notesdemo.domain.model.NotesEntity

interface NotesRepository {
    fun addNote(notesEntity: NotesEntity)
    fun getAllNotes(): List<NotesEntity>?

}