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

    // for db unit testing
    @Query("SELECT * FROM user_notes")
    fun getAllNotesTest(): NotesEntity

    @Query("UPDATE user_notes SET title = :title,description = :description,cardColor = :cardColor,isEdited =:isEdited,createdAt = :createdAt,updatedAt = :updatedAt  Where id = :id")
    fun updateStudentDetails(
        id: Int,
        title: String?,
        description: String?,
        cardColor: String?,
        isEdited: Boolean?,
        createdAt: String?,
        updatedAt: String?
    )
    @Query("SELECT * FROM user_notes WHERE id LIKE :id")
    fun getDataById(id: Int) : NotesEntity
}