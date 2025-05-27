package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.molerocn.deckly.databinding.ActivityMainBinding
import com.molerocn.deckly.presentation.viewmodel.CardViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val cardViewModel: CardViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        cardViewModel.onCreate()
        cardViewModel.cardModel.observe(this, Observer {
            binding.tvCardFront.text = it.front
        })
        cardViewModel.isLoading.observe(this, Observer {
            binding.progress.isVisible = it
        })
    }
}