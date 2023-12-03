package com.example.notesdemo.presentation.notes.viewmodel

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.data.repository.NotesRepositoryImpl
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository
import com.example.notesdemo.domain.use_cases.AddNotesUseCase
import kotlinx.coroutines.launch


class NotesViewModel : ViewModel() {

    private val notesRepository: NotesRepository by lazy {
        NotesRepositoryImpl(NotesApplication.notesDatabase)
    }
    private val notesUseCase: AddNotesUseCase by lazy {
        AddNotesUseCase(notesRepository)
    }

    fun addNotes(notesEntity: NotesEntity, onNoteAdded: (String) -> Unit) {
        viewModelScope.launch {
            notesUseCase.addNotes(notesEntity)
            onNoteAdded.invoke("Note Added Successfully !!")
        }
    }

    fun getAllNotes(): List<NotesEntity>? {
        var notesEntity: List<NotesEntity>? = null
        viewModelScope.launch {
            notesEntity = notesUseCase.getAllNotes()
        }
        return notesEntity
    }
}