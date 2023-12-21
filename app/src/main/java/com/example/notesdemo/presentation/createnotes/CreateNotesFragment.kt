package com.example.notesdemo.presentation.createnotes

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.notesdemo.NotesApplication
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentCreateNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.presentation.createnotes.adapter.ColorPaletteAdapter
import com.example.notesdemo.presentation.createnotes.model.ColorPaletteModel
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModelFactory
import com.example.notesdemo.utils.ColorConstants
import com.example.notesdemo.utils.ExtensionClass.replaceFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class CreateNotesFragment : Fragment() {
    private var binding: FragmentCreateNotesBinding? = null
    private var selectedColor: ColorPaletteModel? = null
    private var notesViewModel: NotesViewModel? = null
    var editNoteData: NotesEntity? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater)
        notesViewModel = ViewModelProvider(
            this,
            NotesViewModelFactory((requireActivity().application as NotesApplication).repository)
        )[NotesViewModel::class.java]

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorOptionsAdapter()
        prepopulateDataToUpdate()
        createNotes()
    }

    private fun prepopulateDataToUpdate() {
        if (notesViewModel?.editableNotesID != 0) {
            CoroutineScope(Dispatchers.Default).launch {
                // Observe changes in the shared data
                editNoteData = notesViewModel?.getDataById()
                Log.e("title------",editNoteData?.title.toString())
                binding?.apply {
                    etTitle.setText(editNoteData?.title.toString())
                    etDesc.setText(editNoteData?.description.toString())
                }
            }
        }
    }

    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())
        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
        adapter.onItemClicked = { colorList ->
            selectedColor = colorList
            Log.e("color Adapter click", colorList.toString())
        }
    }

    private fun createNotes() {
        setColorOptionsAdapter()
        binding?.btnSaveNote?.setOnClickListener {
            if (binding?.etTitle?.text.isNullOrBlank() || binding?.etDesc?.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "fill notes details first", Toast.LENGTH_SHORT)
                    .show()
            } else {
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
                    replaceFragment(R.id.mainFragment, ViewNotesFragment(), false)
                }
            }
        }
    }

}



