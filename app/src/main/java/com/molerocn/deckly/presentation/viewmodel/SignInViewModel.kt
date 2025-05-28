package com.molerocn.deckly.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.core.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import com.molerocn.deckly.domain.SignInWithGoogle
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authHelper: AuthHelper,
    private val signInWithGoogle: SignInWithGoogle
) : ViewModel() {

    fun signInWithGoogle() {
        viewModelScope.launch {
            val token = authHelper.getTokenFromGoogle()
            signInWithGoogle(token)
        }
    }
}
