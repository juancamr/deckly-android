package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.molerocn.deckly.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
    }

}

// cardViewModel.onCreate()
// cardViewModel.cardModel.observe(this, Observer {
//     binding.tvCardFront.text = it.front
// })
// cardViewModel.isLoading.observe(this, Observer {
//     binding.progress.isVisible = it
// })
