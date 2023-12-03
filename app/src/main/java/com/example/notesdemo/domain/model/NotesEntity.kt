package com.example.notesdemo.domain.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.Date

@Entity(tableName = "user_notes")
data class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Long? = 0,
    val title: String?,
    val description: String?,
    val cardColor: String?,
    val isEdited: Boolean? = false,
    val createdAt: String? = "",
    val updatedAt: String? = ""
)
