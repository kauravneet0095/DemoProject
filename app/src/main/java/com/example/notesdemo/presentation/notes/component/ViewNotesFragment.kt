package com.example.notesdemo.presentation.notes.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.R
import com.example.notesdemo.data.repository.NotesRepositoryImpl
import com.example.notesdemo.databinding.FragmentViewNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.presentation.notes.component.adapter.NotesAdapter
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModelFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewNotesFragment : Fragment() {
    private var binding: FragmentViewNotesBinding? = null
    private var notesViewModel: NotesViewModel? = null
    private lateinit var notesAdapter: NotesAdapter
    private var notesRepositoryImpl: NotesRepositoryImpl? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewNotesBinding.inflate(inflater)
        notesViewModel = ViewModelProvider(
            this,
            NotesViewModelFactory((requireActivity().application as NotesApplication).repository)
        )[NotesViewModel::class.java]
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        CoroutineScope(Dispatchers.Main).launch {
            notesViewModel?.getAllNotes()?.observe(viewLifecycleOwner, object : Observer<List<NotesEntity>>  {
                override fun onChanged(value: List<NotesEntity>) {
                    setUpRv(value)

                }

            })
        }
    }

    private fun setUpRv(notesList: List<NotesEntity>) {
        val recyclerView: RecyclerView? = view?.findViewById<RecyclerView>(R.id.rv_notes)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.layoutManager = staggeredGridLayoutManager
        val adapter = NotesAdapter(notesList, requireContext())
        recyclerView?.adapter = adapter
    }
}