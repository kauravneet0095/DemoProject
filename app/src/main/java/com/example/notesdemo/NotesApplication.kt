package com.example.notesdemo

import android.app.Application
import android.content.Context
import com.example.notesdemo.data.data_source.db.NotesDatabase

class NotesApplication: Application() {

    companion object {
        var notesDatabase: NotesDatabase? = null
    }

    override fun onCreate() {
        super.onCreate()
        notesDatabase = initialiseDB(applicationContext)
    }

    private fun initialiseDB(context: Context): NotesDatabase? {
        return NotesDatabase.getInstance(context)
    }

}