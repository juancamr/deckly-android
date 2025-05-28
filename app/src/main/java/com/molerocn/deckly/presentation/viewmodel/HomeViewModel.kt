package com.molerocn.deckly.presentation.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
) : ViewModel() {

    fun onCreate() {
        Log.i("HomeViewModel", "onCreate called")
    }
}
