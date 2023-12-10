package com.example.notesdemo.data.data_source.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.notesdemo.domain.model.NotesEntity

@Database(entities = [NotesEntity::class], version = 2)
abstract class NotesDatabase : RoomDatabase() {
    abstract fun notesDao(): NotesDao
    companion object {
        @Volatile
        var instance: NotesDatabase? = null
        private const val DATABASE_NAME = "notes_database"

        fun getInstance(context: Context): NotesDatabase {
            return instance ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    NotesDatabase::class.java,
                    DATABASE_NAME
                ).fallbackToDestructiveMigration().build()
                Companion.instance = instance
                // return instance
                instance
            }
        }
    }
}