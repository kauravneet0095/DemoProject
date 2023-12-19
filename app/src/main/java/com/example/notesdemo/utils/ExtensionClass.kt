package com.example.notesdemo.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

object ExtensionClass {
    fun AppCompatActivity.replaceFragment(fragmentView: Int, fragment: Fragment) {
        val fragmentTransaction = supportFragmentManager.beginTransaction()
        fragmentTransaction.replace(fragmentView, fragment)
        fragmentTransaction.addToBackStack(fragment.tag)
        fragmentTransaction.commit()
    }
}