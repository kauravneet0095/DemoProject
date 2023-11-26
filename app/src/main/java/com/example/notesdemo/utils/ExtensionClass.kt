package com.example.notesdemo.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object ExtensionClass {
    fun AppCompatActivity.replaceFragment(fragmentView: Int, fragment: Fragment) {
        val fragmentManager = supportFragmentManager
        val transaction = fragmentManager.beginTransaction()
        transaction.replace(fragmentView, fragment)
        transaction.addToBackStack(null)
        transaction.commit()
    }
}