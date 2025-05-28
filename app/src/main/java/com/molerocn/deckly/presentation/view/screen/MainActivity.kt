package com.molerocn.deckly.presentation.view.screen

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.findViewTreeLifecycleOwner
import com.molerocn.deckly.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint
import com.molerocn.deckly.presentation.viewmodel.SignInViewModel

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val signInViewModel: SignInViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        signInViewModel.navigationEvent.observe(this, {
            if (it) {
                val intent = Intent(this, HomeActivity::class.java)
                val userName = signInViewModel.user.value?.name ?: ""
                intent.putExtra("user", userName)
                startActivity(intent)
                signInViewModel.navigationCompleted()
            }
        })

        binding.btnGoogleSignIn.setOnClickListener {
            signInViewModel.signInWithGoogle()
            signInViewModel.navigate()
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
