package com.example.notesdemo.presentation.notes.viewmodel

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository
import com.example.notesdemo.domain.use_cases.AddNotesUseCase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

open class NotesViewModel(repository: NotesRepository) : ViewModel() {

    var editableNotesID: Int = 0


    private val notesUseCase: AddNotesUseCase by lazy {
        AddNotesUseCase(repository)
    }

    open fun validateFields(title: String, desc: String): Boolean {
        return !(title.isEmpty()|| desc.isEmpty())
    }



    fun addNotes(
        notesEntity: NotesEntity,
        context: Context,
        onNoteAdded: (String) -> Unit
    ) {
        viewModelScope.launch {
            notesUseCase.addNotes(notesEntity, context)
            onNoteAdded.invoke("Note Added Successfully !!")
        }
    }

    fun updateStudentDetails(
        context: Context,
        notesEntity: NotesEntity,
        onNoteAdded: (String) -> Unit
    ) {
        viewModelScope.launch {
            notesUseCase.updateStudentDetails(context, notesEntity)
            onNoteAdded.invoke("Note updated Successfully !!")
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

    suspend fun getDataById(): NotesEntity? {
        var notesEntity: NotesEntity? = null
        val job = CoroutineScope(Dispatchers.IO).launch {
            notesEntity = notesUseCase.getDataById(editableNotesID)
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