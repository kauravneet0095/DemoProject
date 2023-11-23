package com.example.notesdemo.presentation.createnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notesdemo.databinding.FragmentCreateNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.presentation.createnotes.adapter.ColorPaletteAdapter
import com.example.notesdemo.presentation.createnotes.model.ColorPaletteModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.utils.ColorConstants

class CreateNotesFragment : Fragment() {
    private var binding: FragmentCreateNotesBinding? = null
    private var selectedColor: ColorPaletteModel? = null
    private var notesViewModel: NotesViewModel? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorOptionsAdapter()
        createNotes()
    }

    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())

        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
        adapter.onItemClicked = { colorList ->
            selectedColor = colorList
        }
    }

    private fun createNotes() {
        setColorOptionsAdapter()
        binding?.btnSaveNote?.setOnClickListener {
            if (binding?.etTitle?.text.isNullOrBlank() || binding?.etDesc?.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "fill notes details first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                notesViewModel?.addNotes(
                    NotesEntity(
                        123,
                        binding?.etTitle?.text.toString(),
                        binding?.etDesc?.text.toString(),
                        selectedColor?.paletteColor.toString(),
                        false, "", ""
                    )
                ) { message ->
                    println(message)
                }
            }
        }
    }

}
