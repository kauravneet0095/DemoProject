package com.example.notesdemo.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.notesdemo.R
import com.example.notesdemo.adapter.vh.ColorPaletteVH
import com.example.notesdemo.data.model.ColorPaletteModel

class ColorPaletteAdapter(private val colorList: ArrayList<ColorPaletteModel>) :
    RecyclerView.Adapter<ColorPaletteVH>() {
    private var selectedItemPosition: Int = RecyclerView.NO_POSITION
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
        val currentItem = colorList[position]

        // Set the background color
        currentItem.paletteColor?.let {
            holder.binding?.cardColorPalette?.setBackgroundColor(it)
        }

        // Set the visibility of the selected indicator
        holder.binding?.tvSelected?.visibility = if (currentItem.isSelected) View.VISIBLE else View.GONE

        // Set up item click listener
        holder.binding?.cardColorPalette?.setOnClickListener {
            // Clear selection on the previously selected item
            if (selectedItemPosition != RecyclerView.NO_POSITION) {
                val prevSelectedItem = colorList[selectedItemPosition]
                prevSelectedItem.isSelected = false
                notifyItemChanged(selectedItemPosition)
            }

            // Update selection on the newly clicked item
            currentItem.isSelected = !currentItem.isSelected
            selectedItemPosition = position

            // Toggle visibility of the selected indicator
            holder.binding?.tvSelected?.visibility = if (currentItem.isSelected) View.VISIBLE else View.GONE

            // Notify the listener about the item click
            onItemClicked?.invoke(currentItem)
        }
    }}



/*   override fun onBindViewHolder(holder: ColorPaletteVH, position: Int) {
       colorList[position].paletteColor?.let {
           holder.binding?.cardColorPalette?.setBackgroundColor(
               it
           )
       }
       holder.binding?.cardColorPalette?.setOnClickListener {
           if (holder.binding?.tvSelected?.visibility == View.VISIBLE) {
               holder.binding?.tvSelected?.visibility = View.GONE
           } else {
               holder.binding?.tvSelected?.visibility = View.VISIBLE
           }
           onItemClicked?.invoke(colorList[position])
       }

   }
}*/


/**
 * from chat gpt but not working
 */

//    override fun onBindViewHolder(holder: ColorPaletteVH, position: Int) {
//        val colorPalette = colorList[position]
//        colorList[position].paletteColor?.let {
//            holder.binding?.cardColorPalette?.setBackgroundColor(
//                it
//            )
//            // Set up item click listener
//            holder.binding?.cardColorPalette?.setOnClickListener {
//                // Clear selection on the previously selected item
//                selectedItem?.isSelected = false
//                val previousSelectedPosition = colorList.indexOf(selectedItem)
//                if (previousSelectedPosition != RecyclerView.NO_POSITION) {
//                    notifyItemChanged(previousSelectedPosition)
//                }
//
//                // Update selection on the newly clicked item
//                colorPalette.isSelected = true
//                selectedItem = colorPalette
//
//                // Notify the listener about the item click
//                onItemClicked?.invoke(colorList[position])
//
//                // Notify the adapter about the data change
//                notifyItemChanged(position)
//
//                // Update views based on the selection
//                holder.binding?.tvSelected?.visibility =
//                    if (colorPalette.isSelected) View.VISIBLE else View.GONE
//
//            }
//        }
//    }
//}



