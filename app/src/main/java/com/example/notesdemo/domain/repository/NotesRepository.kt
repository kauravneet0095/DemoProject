package com.example.notesdemo.domain.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notesdemo.domain.model.NotesEntity

interface NotesRepository {
    fun addNote(notesEntity: NotesEntity,context: Context)
    fun getAllNotes(): LiveData<List<NotesEntity>>?

}