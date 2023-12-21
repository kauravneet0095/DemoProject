package com.example.notesdemo.utils

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction

object ExtensionClass {

    fun AppCompatActivity.replaceFragment(containerId: Int, fragment: Fragment, addToBackStack: Boolean = false) {
        supportFragmentManager.beginTransaction().apply {
            replace(containerId, fragment)

            if (addToBackStack) {
                addToBackStack(fragment.javaClass.simpleName)
            }

            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }
    fun Fragment.replaceFragment(
        containerId: Int,
        fragment: Fragment,
        addToBackStack: Boolean = false
    ) {
        activity?.supportFragmentManager?.beginTransaction()?.apply {
            replace(containerId, fragment)
            if (addToBackStack) {
                addToBackStack(fragment.javaClass.simpleName)
            }
            setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            commit()
        }
    }
}