package com.example.notesdemo.presentation.notes.component

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentViewNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.interfaces.OnItemClickListener
import com.example.notesdemo.presentation.createnotes.CreateNotesFragment
import com.example.notesdemo.presentation.mainactivity.components.MainActivity
import com.example.notesdemo.presentation.notes.component.adapter.NotesAdapter
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModelFactory
import com.example.notesdemo.presentation.updatenotes.UpdateNotesFragment
import com.example.notesdemo.utils.ExtensionClass.replaceFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ViewNotesFragment : Fragment() {
    private var binding: FragmentViewNotesBinding? = null
    private var notesViewModel: NotesViewModel? = null
    private var mainActivity: MainActivity? = null


    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

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
        mainActivity?.setAppBarTitle(getString(R.string.view_notes), false)
        observeViewModel()
        setListeners()
    }

    private fun observeViewModel() {
        CoroutineScope(Dispatchers.Main).launch {
            notesViewModel?.getAllNotes()?.observe(
                viewLifecycleOwner
            ) { value ->
                if (value.isEmpty()) {
                    binding?.apply {
                        rvNotes.visibility = View.GONE
                        tvEmptyDb.visibility = View.VISIBLE
                    }
                } else {
                    binding?.apply {
                        tvEmptyDb.visibility = View.GONE
                        rvNotes.visibility = View.VISIBLE
                    }
                }
                setUpRv(value)
            }
        }

    }

    private fun setListeners() {
        binding?.fabCreateNotes?.setOnClickListener {
            replaceFragment(R.id.mainFragment, CreateNotesFragment(), true)

        }
    }

    private fun setUpRv(notesList: List<NotesEntity>) {
        val adapter = NotesAdapter(notesList, requireContext(), object : OnItemClickListener {
            override fun onItemClick(position: Int) {
                val receivingFragment = UpdateNotesFragment()
                val bundle = Bundle()
                bundle.putString("id", position.toString())
                receivingFragment.arguments = bundle

                replaceFragment(R.id.mainFragment, receivingFragment, true)
            }
        })
        binding?.rvNotes?.adapter = adapter
    }


}