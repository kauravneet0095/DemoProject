package com.example.notesdemo.data.repository

import android.content.Context
import androidx.lifecycle.LiveData
import com.example.notesdemo.data.data_source.db.NotesDatabase
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class NotesRepositoryImpl constructor(
    private var notesDatabase: NotesDatabase?
) : NotesRepository {

    private fun initialiseDB(context: Context): NotesDatabase? {
        return NotesDatabase.getInstance(context)
    }

    override fun addNote(notesEntity: NotesEntity, context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            initialiseDB(context)
            notesDatabase?.notesDao()?.addNotes(notesEntity)
        }
    }

    override fun getAllNotes(context: Context): LiveData<List<NotesEntity>> {
        notesDatabase = initialiseDB(context)
        return notesDatabase!!.notesDao().getAllNotes()
    }
}


