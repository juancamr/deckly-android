package com.molerocn.deckly.data.repository

import com.molerocn.deckly.data.model.CardModel
import com.molerocn.deckly.data.network.CardService
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val api: CardService,
) {

    suspend fun getAllCards(): List<CardModel> {
        val response = api.getCards()
        return response
    }
}