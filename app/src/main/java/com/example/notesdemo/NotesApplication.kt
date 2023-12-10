package com.example.notesdemo

import android.app.Application
import com.example.notesdemo.data.data_source.db.NotesDatabase
import com.example.notesdemo.data.repository.NotesRepositoryImpl
import com.example.notesdemo.domain.repository.NotesRepository

class NotesApplication: Application() {

    private val database by lazy { NotesDatabase.getInstance(this) }
    val repository by lazy { NotesRepositoryImpl(database?.notesDao()) }


}