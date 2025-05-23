package com.juancamr.deckly.data

import com.juancamr.deckly.data.model.CardModel
import com.juancamr.deckly.data.model.CardProvider
import com.juancamr.deckly.data.network.CardService

class CardRepository {

    private val api = CardService()

    suspend fun getAllCards(): List<CardModel> {
        val response = api.getCards()
        CardProvider.cards = response
        return response
    }
}