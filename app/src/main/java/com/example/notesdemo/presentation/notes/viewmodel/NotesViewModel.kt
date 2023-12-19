package com.example.notesdemo.presentation.notes.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.data.repository.NotesRepositoryImpl
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository
import com.example.notesdemo.domain.use_cases.AddNotesUseCase
import com.example.notesdemo.presentation.notes.component.model.NotesModel
import kotlinx.coroutines.launch

class NotesViewModel(repository: NotesRepository) : ViewModel() {

    private val notesUseCase: AddNotesUseCase by lazy {
        AddNotesUseCase(repository)
    }

    fun addNotes(notesEntity: NotesEntity,context: Context, onNoteAdded: (String) -> Unit) {
        viewModelScope.launch {
            notesUseCase.addNotes(notesEntity,context)
            onNoteAdded.invoke("Note Added Successfully !!")
        }
    }

    suspend fun getAllNotes(): LiveData<List<NotesEntity>>? {
        var notesEntity: LiveData<List<NotesEntity>>? = null
        val job = viewModelScope.launch {
            notesEntity = notesUseCase.getAllNotes()
        }
        job.join()
        return notesEntity
    }
}

class NotesViewModelFactory(private val repository: NotesRepository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(NotesViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return NotesViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}