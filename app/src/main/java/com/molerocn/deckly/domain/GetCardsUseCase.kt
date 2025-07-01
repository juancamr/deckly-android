package com.molerocn.deckly.domain

import com.molerocn.deckly.data.repository.CardRepository
import com.molerocn.deckly.domain.model.Card
import javax.inject.Inject

class GetCardsUseCase @Inject constructor(private val repository: CardRepository) {

    suspend operator fun invoke(): List<Card> {
        // TODO: obtener cards desde la base de datos del backend
        return repository.getAllCardsFromDatabase()
    }
}