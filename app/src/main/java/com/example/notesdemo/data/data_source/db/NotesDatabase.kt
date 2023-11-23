package com.example.notesdemo.data.data_source.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.notesdemo.domain.model.NotesEntity

@Database(entities = [NotesEntity::class], version = 1)
abstract class NotesDatabase : RoomDatabase() {

    abstract val notesDao: NotesDao
}