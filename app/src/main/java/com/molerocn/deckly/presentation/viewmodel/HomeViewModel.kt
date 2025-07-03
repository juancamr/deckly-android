package com.molerocn.deckly.presentation.viewmodel

import android.widget.Toast
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.domain.usecase.AddDeckUseCase
import com.molerocn.deckly.domain.model.Deck
import com.molerocn.deckly.domain.usecase.GetDecksUseCase
import com.molerocn.deckly.presentation.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    // private val sendWelcomeNotificationUseCase: SendWelcomeNotificationUseCase,
    private val addDeckUseCase: AddDeckUseCase,
    private val getDecksUseCase: GetDecksUseCase,
) : ViewModel() {

    val openDeckEvent = MutableLiveData<Boolean>()
    val deckItems = MutableLiveData<List<Deck>>()
    val isLoading = MutableLiveData(false)
    val uiMessage = MutableLiveData<UiMessage>()

    fun onCreate() {
        if (deckItems.value.isNullOrEmpty()) {
            isLoading.postValue(true)
            viewModelScope.launch {
                deckItems.postValue(getDecksUseCase())
                isLoading.postValue(false)
            }
        }
    }

    fun createDeck(name: String) {
        isLoading.postValue(true)
        viewModelScope.launch {
            val deck = Deck(
                name = name
            )
            val newDeck = addDeckUseCase(deck)
            var message = ""
            if (newDeck != null) {
                message = "Mazo creado con exito"
                val newItems = (deckItems.value ?: emptyList()) + listOf(newDeck)
                deckItems.postValue(newItems)
                isLoading.postValue(false)
            } else {
                message = "No se pudo crear el mazo"
            }
            uiMessage.postValue(UiMessage(true, message))
        }
    }

    fun openDeck() {
        openDeckEvent.postValue(true)
    }

    fun endOpenDeck() {
        openDeckEvent.postValue(false)
    }
}
