package com.example.notesdemo.presentation.createnotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.R
import com.example.notesdemo.adapter.ColorPaletteAdapter
import com.example.notesdemo.data.model.ColorPaletteModel
import com.example.notesdemo.databinding.FragmentCreateNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModelFactory
import com.example.notesdemo.utils.ColorConstants
import com.example.notesdemo.utils.ExtensionClass.replaceFragment
import kotlinx.coroutines.runBlocking

class CreateNotesFragment : Fragment() {
    private var binding: FragmentCreateNotesBinding? = null
    private var selectedColor: ColorPaletteModel? = null
    private var notesViewModel: NotesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater)
        notesViewModel = ViewModelProvider(
            this,
            NotesViewModelFactory((requireActivity().application as NotesApplication).repository)
        )[NotesViewModel::class.java]
        binding?.notesViewModel = notesViewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Create Notes"
        setColorOptionsAdapter()
        setListeners()
    }

    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())
        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
        adapter.onItemClicked = { it ->
            selectedColor = it
            Log.e("color Adapter clicked from CreateNotesFragment", colorList.toString())
        }
    }

    fun validateDataFields(): Boolean {
        return if (notesViewModel?.isTitleEmpty() == true) {
            Toast.makeText(requireContext(), "fill title first", Toast.LENGTH_SHORT)
                .show()
            false
        } else if (notesViewModel?.isDescEmpty() == true) {
            Toast.makeText(requireContext(), "fill desc first", Toast.LENGTH_SHORT)
                .show()
            false
        } else {
            true
        }
    }
    private fun setListeners() {
        setColorOptionsAdapter()
        binding?.btnSaveNote?.setOnClickListener {
            if (validateDataFields()) {
                val notesEntity = NotesEntity(
                    0, title = binding?.etTitle?.text.toString(),
                    description = binding?.etDesc?.text.toString(),
                    cardColor = selectedColor?.paletteColor.toString()
                )

                notesViewModel?.addNotes(
                    notesEntity, requireContext()
                ) { message ->
                    println(message)
                    runBlocking {
                        notesViewModel?.getAllNotes()?.observe(
                            viewLifecycleOwner
                        ) { value -> Log.e("NotesDb", value?.size.toString()) }
                    }
                    replaceFragment(R.id.mainFragment, ViewNotesFragment(), true)
                }
            }
        }
    }
}



