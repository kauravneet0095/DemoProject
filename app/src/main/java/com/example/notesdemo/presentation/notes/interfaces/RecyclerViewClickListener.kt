package com.example.notesdemo.presentation.notes.interfaces

import android.view.View

interface RecyclerViewClickListener {
    fun recyclerViewListClicked(v: View?, position: Int)
}