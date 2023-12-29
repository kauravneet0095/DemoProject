package com.example.notesdemo

import android.os.Bundle
import android.view.View
import android.view.ViewTreeObserver
import androidx.appcompat.app.AppCompatActivity
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.notesdemo.databinding.ActivityMainBinding
import com.example.notesdemo.presentation.notes.component.ViewNotesFragment
import com.example.notesdemo.utils.ExtensionClass.replaceFragment

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    lateinit var splashViewModel: SplashViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        installSplashScreen()
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        splashViewModel = ViewModelProvider(this)[SplashViewModel::class.java]
        val content: View = findViewById(android.R.id.content)
        content.viewTreeObserver.addOnPreDrawListener(object : ViewTreeObserver.OnPreDrawListener {


            override fun onPreDraw(): Boolean {
                return if (splashViewModel.isReady()) {
                    content.viewTreeObserver.removeOnPreDrawListener(this)
                    true
                } else {
                    return false
                }
            }

        })

        if (savedInstanceState == null) {
            replaceFragment(R.id.mainFragment, ViewNotesFragment())
        }
    }


}

