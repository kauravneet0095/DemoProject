package com.example.notesdemo.data.db

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.notesdemo.data.data_source.db.NotesDao
import com.example.notesdemo.data.data_source.db.NotesDatabase
import com.example.notesdemo.domain.model.NotesEntity
import com.google.common.truth.Truth
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class NotesDaoTest {

     @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var notesDao: NotesDao
    private lateinit var notesDatabase: NotesDatabase

    @Before
    fun setUp() {
        notesDatabase = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            NotesDatabase::class.java
        ).build()

        notesDao = notesDatabase.notesDao()
    }

    @After
    fun tearDown() {
        notesDatabase.close()
    }

    @Test
    fun addNotesTest() : Unit = runBlocking {
        val notes = NotesEntity(123,"Title test","test desc","",false,"","")
        notesDao.addNotes(notes)

        val getAllNotes = notesDao.getAllNotesTest()
        Truth.assertThat(getAllNotes)
    }
}