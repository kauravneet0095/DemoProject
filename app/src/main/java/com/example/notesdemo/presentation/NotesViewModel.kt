package com.example.notesdemo.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.notesapp.notes.domain.model.NotesEntity
import com.example.notesdemo.domain.use_cases.AddNotesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class NotesViewModel @Inject constructor(private val notesUseCase: AddNotesUseCase): ViewModel() {

    private var addNotesJob: Job? = null

    fun addNotes(notesEntity: NotesEntity, onNoteAdded: (String) -> Unit) {
        viewModelScope.launch {
            notesUseCase.addNotes(notesEntity)
            onNoteAdded.invoke("Note Added Successfully !!")
        }
    }

    fun editNotes(notesEntity: NotesEntity, onNoteEdited: (String) -> String) {
        viewModelScope.launch {
            notesUseCase.editNotes(notesEntity)
            onNoteEdited.invoke("Note Edited Successfully !!")
        }
    }
}