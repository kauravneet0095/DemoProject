package com.example.notesdemo.presentation.createnotes

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.notesdemo.databinding.FragmentCreateNotesBinding
import com.example.notesdemo.presentation.createnotes.adapter.ColorPaletteAdapter
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
        setColorOptionsAdapter();
    }

    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())
        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
    }
}