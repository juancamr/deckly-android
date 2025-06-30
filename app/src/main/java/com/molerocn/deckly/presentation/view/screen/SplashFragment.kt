package com.molerocn.deckly.presentation.view.screen

import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.molerocn.deckly.R
import com.molerocn.deckly.core.AuthHelper
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class SplashFragment @Inject constructor(
    private val authHelper: AuthHelper
) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val isLoggedIn = authHelper.isLoggedIn()
        val navController = findNavController()

        if (isLoggedIn) {
            navController.navigate(R.id.action_splashFragment_to_main_graph)
        } else {
            navController.navigate(R.id.action_splashFragment_to_auth_graph)
        }
    }

}
