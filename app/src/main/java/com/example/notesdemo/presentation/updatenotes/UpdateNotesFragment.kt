package com.example.notesdemo.presentation.updatenotes

import android.content.Context
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
import com.example.notesdemo.databinding.FragmentUpdateNotesBinding
import com.example.notesdemo.domain.model.NotesEntity
import com.example.notesdemo.presentation.mainactivity.components.MainActivity
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModel
import com.example.notesdemo.presentation.notes.viewmodel.NotesViewModelFactory
import com.example.notesdemo.utils.ColorConstants
import com.example.notesdemo.utils.ExtensionClass.replaceFragment
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class UpdateNotesFragment : Fragment() {
    private var binding: FragmentUpdateNotesBinding? = null
    private var selectedColor: ColorPaletteModel? = null
    private var notesViewModel: NotesViewModel? = null
    private var mainActivity: MainActivity? = null
    private var editableDataId = 0

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        binding = FragmentUpdateNotesBinding.inflate(inflater)
        notesViewModel = ViewModelProvider(
            this,
            NotesViewModelFactory((requireActivity().application as NotesApplication).repository)
        )[NotesViewModel::class.java]

        // Retrieve data from arguments
        editableDataId = arguments?.getString("id").toString().toInt()
        binding?.notesViewModel = notesViewModel
        binding?.lifecycleOwner = this
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mainActivity?.setAppBarTitle(getString(R.string.def_text_update_notes), true)
        setColorOptionsAdapter()
        prepopulateDataToUpdate()
        setListeners()
    }

    private fun prepopulateDataToUpdate() {
        if (editableDataId != 0) {
            Log.e("editableID", editableDataId.toString())

            CoroutineScope(Dispatchers.Main).launch {
                notesViewModel?.getDataById(editableDataId)?.observe(
                    viewLifecycleOwner
                ) { value ->
                    Log.e("getbyid", value.title.toString())

                    binding?.apply {
                        etTitle.setText(value?.title.toString())
                        etDesc.setText(value?.description.toString())
                    }
                }

            }
        }
    }


    private fun setColorOptionsAdapter() {
        val colorList = ColorConstants.getColors(requireContext())
        val adapter = ColorPaletteAdapter(colorList)
        binding?.rvColorPalette?.adapter = adapter
        adapter.onItemClicked = { it ->
            selectedColor = it
            Log.e("color Adapter click", colorList.toString())
        }
    }

    private fun setListeners() {
        setColorOptionsAdapter()
        binding?.btnUpdateNote?.setOnClickListener {
            if (binding?.etTitle?.text.isNullOrBlank() || binding?.etDesc?.text.isNullOrBlank()) {
                Toast.makeText(requireContext(), "fill notes details first", Toast.LENGTH_SHORT)
                    .show()
            } else {
                val notesEntity = NotesEntity(
                    editableDataId, title = binding?.etTitle?.text.toString(),
                    description = binding?.etDesc?.text.toString(),
                    cardColor = selectedColor?.paletteColor.toString()
                )

                notesViewModel?.updateNotes(
                    requireContext(), notesEntity,
                ) { message ->
                   requireActivity().supportFragmentManager.popBackStack()
                }
            }
        }
    }

}