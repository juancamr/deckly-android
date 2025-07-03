package com.molerocn.deckly.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.molerocn.deckly.domain.model.Card
import com.molerocn.deckly.domain.model.Deck
import com.molerocn.deckly.domain.usecase.AddCardUseCase
import com.molerocn.deckly.domain.usecase.GetDecksUseCase
import com.molerocn.deckly.presentation.UiMessage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AddCardViewModel @Inject constructor(
    private val getDecksUseCase: GetDecksUseCase,
    private val addCardUseCase: AddCardUseCase
) : ViewModel() {


    val uiMessage = MutableLiveData<UiMessage>()
    val deckItems = MutableLiveData<List<Deck>>()
    val selectedDeck = MutableLiveData<Deck>()
    val addedCompleted = MutableLiveData<Boolean>()

    fun onCreate() {
        viewModelScope.launch {
            deckItems.postValue(getDecksUseCase())
        }
    }

    fun createCard(front: String, back: String) {
        viewModelScope.launch {
            val card = Card(
                deckId = selectedDeck.value!!.id,
                front = front,
                back = back,
            )
            val newCard = addCardUseCase(card)
            var message = ""
            if (newCard != null) {
                message = "Tarjeta creada con exito"
                addedCompleted.postValue(true)
            } else {
                message = "La tarjeta no pudo ser creada"
            }
            uiMessage.postValue(UiMessage(true, message))
        }
    }
}
