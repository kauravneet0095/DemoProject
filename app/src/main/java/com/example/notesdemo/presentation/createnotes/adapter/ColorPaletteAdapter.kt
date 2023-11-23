package com.example.notesdemo.presentation.createnotes.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.R
import com.example.notesdemo.presentation.createnotes.model.ColorPaletteModel
import com.example.notesdemo.presentation.createnotes.adapter.vh.ColorPaletteVH

class ColorPaletteAdapter(private val colorList: ArrayList<ColorPaletteModel>) :
    RecyclerView.Adapter<ColorPaletteVH>() {

    var onItemClicked: ((colorList: ColorPaletteModel) -> Unit)? = null
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorPaletteVH {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.rv_item_color_palettes, parent, false)
        return ColorPaletteVH(view)
    }

    override fun getItemCount(): Int {
        return colorList.size
    }

    override fun onBindViewHolder(holder: ColorPaletteVH, position: Int) {
        colorList[position].paletteColor?.let {
            holder.binding?.cardColorPalette?.setCardBackgroundColor(
                it
            )
        }
        holder.binding?.cardColorPalette?.setOnClickListener {
            onItemClicked?.invoke(colorList[position])
        }
    }
}

