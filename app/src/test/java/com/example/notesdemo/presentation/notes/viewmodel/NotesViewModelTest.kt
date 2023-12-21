package com.example.notesdemo.presentation.notes.viewmodel

import android.content.Context
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.domain.repository.NotesRepository
import com.google.common.truth.Truth.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.Mockito

class NotesViewModelTest {
    private lateinit var notesViewModel: NotesViewModel
    private lateinit var notesRepository: NotesRepository
    private lateinit var notesEntity: NotesEntity
    private lateinit var context: Context

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    @Before
    fun setUp() {
        notesViewModel = Mockito.mock(NotesViewModel::class.java)
        Mockito.`when`(notesViewModel.validateFields("abcd", "abcd")).thenReturn(true)
        Mockito.`when`(notesViewModel.validateFields("", "")).thenReturn(false)
        Mockito.`when`(notesViewModel.validateFields("", "desc")).thenReturn(false)
        Mockito.`when`(notesViewModel.validateFields("title", "")).thenReturn(false)

    }


    @Test
    fun validateEditTextsWithTitleAndDesc() {
        val result = notesViewModel.validateFields("abcd", "abcd")
        assertThat(result).isEqualTo(true)
    }

    @Test
    fun validateEditTextsWithOnlyTitle() {
        val result = notesViewModel.validateFields("abcd", "")
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun validateEditTextsWithOnlyDesc() {
        val result = notesViewModel.validateFields("", "abcd")
        assertThat(result).isEqualTo(false)
    }

    @Test
    fun validateEditTextsWithNullValues() {
        val result = notesViewModel.validateFields("", "")
        assertThat(result).isEqualTo(false)
    }


}