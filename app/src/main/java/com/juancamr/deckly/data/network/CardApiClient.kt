package com.juancamr.deckly.data.network

import com.juancamr.deckly.data.model.CardModel
import retrofit2.Response
import retrofit2.http.GET

interface CardApiClient {
    @GET("/cards")
    suspend fun getAllCards(): Response<List<CardModel>>
}