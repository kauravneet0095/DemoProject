package com.example.notesdemo

import android.os.Bundle
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.notesdemo.presentation.createnotes.CreateNotesFragment
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
class MainActivity : AppCompatActivity() {
    private lateinit var mainFragmentLayout: FrameLayout
    val createNotesFragment : Fragment = CreateNotesFragment()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainFragmentLayout = findViewById(R.id.main_fragment)
        supportFragmentManager.inTransaction {
            add(R.id.main_fragment, createNotesFragment)

        }
    }

    private inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
        val fragmentTransaction = beginTransaction()
        fragmentTransaction.func()
        fragmentTransaction.commit()
    }
}