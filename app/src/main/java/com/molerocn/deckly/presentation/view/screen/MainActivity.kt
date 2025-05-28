package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.credentials.CredentialManager
import com.molerocn.deckly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.molerocn.deckly.presentation.viewmodel.SignInViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val signViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnGoogleSignIn.setOnClickListener {
            signViewModel.signInWithGoogle()
        }
    }


}

// cardViewModel.onCreate()
// cardViewModel.cardModel.observe(this, Observer {
//     binding.tvCardFront.text = it.front
// })
// cardViewModel.isLoading.observe(this, Observer {
//     binding.progress.isVisible = it
// })

// credentialManager.clearCredentialState(ClearCredentialStateRequest()) para cerrar sesion
