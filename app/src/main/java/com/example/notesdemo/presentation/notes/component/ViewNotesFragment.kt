package com.example.notesdemo.presentation.notes.component

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.notesdemo.R
import com.example.notesdemo.databinding.FragmentViewNotesBinding
import com.example.notesdemo.presentation.notes.adapter.NotesAdapter
import com.example.notesdemo.presentation.notes.model.NotesModel
import com.example.notesdemo.utils.enums.ColorsEnum

class ViewNotesFragment : Fragment() {
    private var binding: FragmentViewNotesBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentViewNotesBinding.inflate(inflater)
        return binding?.rvNotes
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setUpRv(dataList())

    }

    fun dataList(): ArrayList<NotesModel> {
        var notesList: ArrayList<NotesModel> = arrayListOf()
        val l1 = NotesModel(
            "Coffee Lets go for a walk and participate in marathon",
            "Please make coffee and Lets go for a walk and participate in marathon Lets go for a walk and participate in marathonLets go for a walk and participate in marathon",
            "Today, 04:30",
            ColorsEnum.GRAY.toArgb(requireContext())
        )
        notesList.add(l1)
        val l2 = NotesModel(
            "Tea",
            "Please make Tea and get out of the world",
            "Today, 04:50",
            ColorsEnum.MAROON.toArgb(requireContext())
        )
        notesList.add(l2)
        val l3 = NotesModel(
            "Meeting on evening",
            "Please make coffee i am getting late for meeting",
            "Today, 04:30",
            ColorsEnum.Magenta.toArgb(requireContext())
        )

        notesList.add(l3)
        val l5 = NotesModel(
            "Coffee",
//            desc = "Please make coff",
            time = "Today, 04:30", bgColor = ColorsEnum.RED.toArgb(requireContext())
        )
        notesList.add(l5)
        val l4 = NotesModel(
            "Evening walk",
            "Lets go for a walk and participate in marathon organised on 29th oct 2023 in chandigarh",
            "Tomorrow, 04:30",
            ColorsEnum.PINK.toArgb(requireContext())

        )
        notesList.add(l4)

        val l6 = NotesModel(
            "Coffee",
            "Please make coffee Please make coffee\n Please make coffee",
            "Today, 04:30",
            ColorsEnum.GRAY.toArgb(requireContext())
        )
        notesList.add(l6)
        val l7 = NotesModel(
            "Coffee",
            "Please make coffee Please make coffee\n Please make coffee",
            "Today, 04:30",
            ColorsEnum.Magenta.toArgb(requireContext())
        )
        notesList.add(l7)
        val l8 = NotesModel(
            title = "Coffee",
            desc = "Please make coffee Please make coffee\n Please make coffee",
            "Today, 04:30",
            ColorsEnum.ORANGE.toArgb(requireContext())
        )
        notesList.add(l8)
        val l9 = NotesModel(
            title = "Check",
//            desc = "Please make coffee Please make coffee\n Please make coffee",
            time = "Today, 04:30",
            bgColor = ColorsEnum.MAROON.toArgb(requireContext())
        )
        notesList.add(l9)
        val l10 = NotesModel(
            "Coffee",
            "Please make coffee Please make coffee\n Please make coffee",
            "Today, 04:30",
            ColorsEnum.BROWN.toArgb(requireContext())
        )
        notesList.add(l10)



        return notesList
    }

    private fun setUpRv(notesList: ArrayList<NotesModel>) {
        val recyclerView: RecyclerView? = view?.findViewById<RecyclerView>(R.id.rv_notes)
        val staggeredGridLayoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        recyclerView?.layoutManager = staggeredGridLayoutManager
        val adapter = NotesAdapter(notesList, requireContext())
        recyclerView?.adapter = adapter
    }


}