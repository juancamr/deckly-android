package com.juancamr.deckly.domain

import com.juancamr.deckly.data.CardRepository
import com.juancamr.deckly.data.model.CardModel

class GetCardsUseCase {
    val repository = CardRepository()

    suspend operator fun invoke(): List<CardModel>? = repository.getAllCards()
}