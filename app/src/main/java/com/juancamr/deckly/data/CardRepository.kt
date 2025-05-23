package com.juancamr.deckly.data

import com.juancamr.deckly.data.model.CardModel
import com.juancamr.deckly.data.model.CardProvider
import com.juancamr.deckly.data.network.CardService
import javax.inject.Inject

class CardRepository @Inject constructor(private val api: CardService, private val cardProvider: CardProvider) {

    suspend fun getAllCards(): List<CardModel> {
        val response = api.getCards()
        cardProvider.cards = response
        return response
    }
}