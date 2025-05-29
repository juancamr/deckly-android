package com.molerocn.deckly.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.core.AuthHelper
import dagger.hilt.android.lifecycle.HiltViewModel
import com.molerocn.deckly.domain.SignInWithGoogleUseCase
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val authHelper: AuthHelper,
    private val signInWithGoogle: SignInWithGoogleUseCase,
) : ViewModel() {

    // todo: wtf stack overflow? averiguar por que se hace esto y no se crea una variable solamente
    private val _loginSuccess = MutableLiveData<Boolean>()
    val loginSuccess: LiveData<Boolean> = _loginSuccess

    fun signInWithGoogle() {
        viewModelScope.launch {
            val token = authHelper.getTokenFromGoogle()
            val response = signInWithGoogle(token)
            if (response != null) {
                Log.i("user", "Nombre del usuario: ${response.name}")
                _loginSuccess.value = true
            } else {
                _loginSuccess.value = false
            }
        }
    }

}
