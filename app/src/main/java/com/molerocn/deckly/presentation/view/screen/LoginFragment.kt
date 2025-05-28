package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.molerocn.deckly.R
import com.molerocn.deckly.databinding.FragmentLoginBinding
import com.molerocn.deckly.presentation.viewmodel.LoginViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        loginViewModel.loginSuccess.observe(viewLifecycleOwner) { success ->
            if (success) {
                Log.i("login", "navegando")
                navController.navigate(R.id.action_global_auth_to_main)
            }
        }

        binding.btnGoogleSignIn.setOnClickListener {
            loginViewModel.signInWithGoogle()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}

// signInViewModel.navigationEvent.observe(this, {
//     if (it) {
//         val intent = Intent(this, HomeActivity::class.java)
//         val userName = signInViewModel.user.value?.name ?: ""
//         intent.putExtra("user", userName)
//         startActivity(intent)
//         signInViewModel.navigationCompleted()
//     }
// })
//