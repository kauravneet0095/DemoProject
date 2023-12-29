package com.example.notesdemo.adapter.vh

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.databinding.RvItemColorPalettesBinding

class ColorPaletteVH(itemView: View) : RecyclerView.ViewHolder(itemView) {
    var binding: RvItemColorPalettesBinding? = null

    init {
        binding = RvItemColorPalettesBinding.bind(itemView)
    }

}