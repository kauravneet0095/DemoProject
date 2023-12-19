package com.example.notesdemo.domain.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_notes")
class NotesEntity(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo val title: String? = "",
    @ColumnInfo val description: String? = "",
    @ColumnInfo val cardColor: String? = "",
    @ColumnInfo val isEdited: Boolean? = false,
    @ColumnInfo val createdAt: String? = "",
    @ColumnInfo val updatedAt: String? = ""
)
