package com.molerocn.deckly.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.molerocn.deckly.data.repository.UserProvider
import com.molerocn.deckly.domain.SendWelcomeNotificationUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val sendWelcomeNotificationUseCase: SendWelcomeNotificationUseCase
) : ViewModel() {

    val nombre = MutableLiveData<String>()
    val goStudyEvent = MutableLiveData<Boolean>()

    fun onCreate() {
        Log.i("home", "on create")
        val sub = UserProvider.userSub
        nombre.postValue(sub)
        sendWelcomeNotificationUseCase(sub!!)
    }

    fun goStudyCard() {
        goStudyEvent.postValue(true)
    }

    fun endGoStudyCard() {
        goStudyEvent.postValue(false)
    }
}
