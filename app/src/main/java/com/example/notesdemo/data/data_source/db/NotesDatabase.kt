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

        fun getInstance(context: Context): NotesDatabase? {
            if (instance == null) {
                synchronized(NotesDatabase::class.java)
                {
                    if (instance == null) {
                        Room.databaseBuilder(
                            context, NotesDatabase::class.java,
                            DATABASE_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build().also { instance = it }
                    }
                }
            }

            return instance
        }
    }
}