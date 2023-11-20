package com.example.notesdemo.utils

import android.content.Context
import androidx.core.content.ContextCompat
import com.example.notesdemo.R
import com.example.notesdemo.presentation.createnotes.model.ColorPaletteModel

object ColorConstants {
    fun getColors(context: Context): ArrayList<ColorPaletteModel> {
        val colorArrayList = ArrayList<ColorPaletteModel>()

        val red = ColorPaletteModel(ContextCompat.getColor(context, R.color.red))
        val pink = ColorPaletteModel(ContextCompat.getColor(context, R.color.pink))
        val maroon = ColorPaletteModel(ContextCompat.getColor(context, R.color.maroon))
        val white = ColorPaletteModel(ContextCompat.getColor(context, R.color.white))
        val gray = ColorPaletteModel(ContextCompat.getColor(context, R.color.gray))
        val brown = ColorPaletteModel(ContextCompat.getColor(context, R.color.brown))
        val magenta = ColorPaletteModel(ContextCompat.getColor(context, R.color.magenta))
        val orange = ColorPaletteModel(ContextCompat.getColor(context, R.color.orange))

        colorArrayList.apply {
            add(red)
            add(pink)
            add(magenta)
            add(maroon)
            add(red)
            add(white)
            add(gray)
            add(brown)
            add(orange)
        }
        return colorArrayList
    }
}