package com.beyzaakkuzu.viewswitcherapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ViewSwitcher
import androidx.lifecycle.ViewModelProvider
import com.beyzaakkuzu.viewswitcherapplication.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    lateinit var viewmodel: MainViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewmodel = ViewModelProvider(this).get(MainViewModel::class.java)
        val b = ActivityMainBinding.inflate(layoutInflater)
        setContentView(b.root)

        viewmodel.getPageState().observe(this) {
            when (it) {
                is MainViewModel.PageState.A -> {
                    b.switcher.switch(b.textA)
                    viewmodel.setB()
                }
                is MainViewModel.PageState.B -> {
                    b.switcher.switch(b.textB)
                    viewmodel.setA()

                }
            }
        }

    }
}

fun ViewSwitcher.switch(view: View) {
    if (nextView == view)
        showNext()
}