package com.example.notesdemo.data.data_source.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.notesdemo.domain.model.NotesEntity

@Dao
interface NotesDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addNotes(notesEntity: NotesEntity)

    @Query("SELECT * FROM user_notes")
    fun getAllNotes(): LiveData<List<NotesEntity>>

}