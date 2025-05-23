package com.juancamr.deckly.data.network

import com.juancamr.deckly.core.RetrofitHelper
import com.juancamr.deckly.data.model.CardModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardService {

    private val retrofit = RetrofitHelper.getRetrofit()

    suspend fun getCards(): List<CardModel> {
        return withContext(Dispatchers.IO) {
            val response = retrofit.create(CardApiClient::class.java).getAllCards()
            response.body() ?: emptyList()
        }
    }
}