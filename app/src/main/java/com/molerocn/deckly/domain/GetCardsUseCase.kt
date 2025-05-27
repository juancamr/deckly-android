package com.molerocn.deckly.domain

import com.molerocn.deckly.data.CardRepository
import com.molerocn.deckly.data.model.CardModel
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: CardRepository) {


    suspend operator fun invoke(): List<CardModel>? = repository.getAllCards()
}