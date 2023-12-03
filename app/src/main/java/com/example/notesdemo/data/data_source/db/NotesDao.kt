package com.example.notesdemo.data.data_source.db

import android.content.Context
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.notesdemo.domain.model.NotesEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotes(notesEntity: NotesEntity)

    @Query("SELECT * FROM user_notes")
    fun getAllNotes(): List<NotesEntity>?

}