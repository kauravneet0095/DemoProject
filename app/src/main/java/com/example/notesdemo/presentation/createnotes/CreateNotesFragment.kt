package com.example.notesdemo.presentation.createnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.notesdemo.databinding.FragmentCreateNotesBinding
import com.example.notesdemo.presentation.createnotes.adapter.ColorPaletteAdapter
import com.example.notesdemo.presentation.notes.model.NotesModel
import com.example.notesdemo.utils.ColorConstants

class CreateNotesFragment : Fragment() {
    private var binding: FragmentCreateNotesBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        binding = FragmentCreateNotesBinding.inflate(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setColorOptionsAdapter()
    }

    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())

        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
    }
    private fun createNotes(){
        binding?.btnSaveNote?.setOnClickListener{
            if (binding?.etTitle?.text.isNullOrBlank() || binding?.etDesc?.text.isNullOrBlank()){
                Toast.makeText(requireContext(),"fill notes details first",Toast.LENGTH_SHORT).show()
            }else{
                val note = NotesModel(
                    binding?.etTitle?.text.toString(),
                    binding?.etDesc?.text.toString(),
                    "",



                )
            }
        }
    }
}