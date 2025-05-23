package com.juancamr.deckly.data.network

import com.juancamr.deckly.data.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CardService @Inject constructor(private val api: CardApiClient) {

    suspend fun getCards(): List<CardModel> {
        return withContext(Dispatchers.IO) {
            val response = api.getAllCards()
            response.body() ?: emptyList()
        }
    }
}