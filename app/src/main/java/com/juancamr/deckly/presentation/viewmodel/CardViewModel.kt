package com.juancamr.deckly.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.juancamr.deckly.data.model.CardModel
import com.juancamr.deckly.data.model.CardProvider
import com.juancamr.deckly.domain.GetCardsUseCase
import kotlinx.coroutines.launch

class CardViewModel: ViewModel() {

    val cardModel = MutableLiveData<CardModel>()
    val isLoading = MutableLiveData<Boolean>()

    // casos de uso
    var getCardsUseCase = GetCardsUseCase()

    fun onCreate() {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getCardsUseCase()

            if (!result.isNullOrEmpty()) {
                cardModel.postValue(result[0])
                isLoading.postValue(false)
            }
        }
    }

    // funcion para obtener un card aleatorio
    fun random() {
        val randomCardIndex = (CardProvider.cards.indices).random()
        val card = CardProvider.cards[randomCardIndex]
        cardModel.postValue(card)
    }
}