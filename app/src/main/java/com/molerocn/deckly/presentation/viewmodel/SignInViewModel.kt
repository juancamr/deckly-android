package com.molerocn.deckly.presentation.viewmodel

import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.core.AuthHelper
import com.molerocn.deckly.data.model.toPresentationModel
import dagger.hilt.android.lifecycle.HiltViewModel
import com.molerocn.deckly.domain.SignInWithGoogle
import com.molerocn.deckly.presentation.model.UserModel
import com.molerocn.deckly.presentation.view.screen.HomeActivity
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SignInViewModel @Inject constructor(
    private val authHelper: AuthHelper,
    private val signInWithGoogle: SignInWithGoogle,
) : ViewModel() {

    val navigationEvent = MutableLiveData<Boolean>()
    val user = MutableLiveData<UserModel>()

    fun signInWithGoogle() {
        viewModelScope.launch {
            val token = authHelper.getTokenFromGoogle()
            user.postValue(signInWithGoogle(token)!!.toPresentationModel())
        }
    }

    fun navigate() {
        navigationEvent.postValue(true)
    }
    
    fun navigationCompleted() {
        navigationEvent.postValue(false)
    }
    
}
