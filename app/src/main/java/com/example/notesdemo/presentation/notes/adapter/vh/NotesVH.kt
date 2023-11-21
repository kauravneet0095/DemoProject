package com.example.notesdemo.presentation.notes.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.databinding.NotesTilesBinding

class NotesVH(itemView: View): RecyclerView.ViewHolder(itemView) {
    var binding: NotesTilesBinding? = null

    init {
        binding = NotesTilesBinding.bind(itemView)
    }
}
