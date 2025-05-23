package com.juancamr.deckly.presentation.view.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import com.juancamr.deckly.databinding.ActivityMainBinding
import com.juancamr.deckly.presentation.viewmodel.CardViewModel

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

        binding.tvCardFront.setOnClickListener {
            cardViewModel.random()
        }
    }
}