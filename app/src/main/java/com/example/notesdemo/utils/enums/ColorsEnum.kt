package com.example.notesdemo.utils.enums

import android.content.Context
import com.example.notesdemo.R

enum class ColorsEnum(private val color: Int) {
    RED(R.color.red),
    PINK(R.color.pink),
    MAROON(R.color.maroon),
    WHITE(R.color.white),
    GRAY(R.color.gray),
    BROWN(R.color.brown),
    Magenta(R.color.magenta),
    ORANGE(R.color.orange);

    fun toArgb(context: Context) =
        context.resources.getColor(color, null)
}