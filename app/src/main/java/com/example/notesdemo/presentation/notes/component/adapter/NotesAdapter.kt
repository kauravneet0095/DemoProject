package com.example.notesdemo.presentation.notes.component.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.R
import com.example.notesdemo.presentation.notes.component.adapter.vh.NotesVH
import com.example.notesdemo.presentation.notes.component.model.NotesModel

class NotesAdapter(private var notesList: ArrayList<NotesModel>, val context: Context) :
    RecyclerView.Adapter<NotesVH>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NotesVH {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.notes_tiles, parent, false)
        return NotesVH(v)
    }

    override fun getItemCount(): Int {
        return notesList.size
    }

    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: NotesVH, position: Int) {
        holder.binding?.tvNotesTitle?.text = notesList[position].title
        holder.binding?.tvNotesDesc?.text = notesList[position].desc
        holder.binding?.tvRemindTime?.text = notesList[position].time
        holder.binding?.layoutMain?.setCardBackgroundColor(notesList[position].bgColor)
    }
    fun setData(notesList: List<NotesModel>) {
        this.notesList = notesList as ArrayList<NotesModel>
        notifyDataSetChanged()
    }
}