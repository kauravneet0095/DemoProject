package com.example.notesdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.notesdemo.databinding.ActivityMainBinding
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.utils.ExtensionClass.replaceFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    val viewNotesFragment: Fragment = ViewNotesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        replaceFragment(R.id.mainFragment, ViewNotesFragment())
    }

}
