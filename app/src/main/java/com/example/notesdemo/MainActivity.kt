package com.example.notesdemo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.notesdemo.databinding.ActivityMainBinding
import com.example.notesdemo.presentation.createnotes.CreateNotesFragment
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.utils.ExtensionClass.replaceFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.fabCreateNotes.setOnClickListener {
            replaceFragment(R.id.mainFragment, CreateNotesFragment())

        }

        if (savedInstanceState == null) {
            replaceFragment(R.id.mainFragment, ViewNotesFragment())
        }
    }


}
