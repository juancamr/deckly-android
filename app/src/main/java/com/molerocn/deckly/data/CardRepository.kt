package com.molerocn.deckly.data

import com.molerocn.deckly.data.model.CardModel
import com.molerocn.deckly.data.model.CardProvider
import com.molerocn.deckly.data.network.CardService
import javax.inject.Inject

class CardRepository @Inject constructor(private val api: CardService, private val cardProvider: CardProvider) {

    suspend fun getAllCards(): List<CardModel> {
        val response = api.getCards()
        cardProvider.cards = response
        return response
    }
}