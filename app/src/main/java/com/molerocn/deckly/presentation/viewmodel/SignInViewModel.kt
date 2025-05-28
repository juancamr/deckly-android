package com.molerocn.deckly.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.core.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authHelper: AuthHelper
) : ViewModel() {

    fun signInWithGoogle() {
        viewModelScope.launch {
            val token = authHelper.signInWithGoogle()
        }
    }
}
