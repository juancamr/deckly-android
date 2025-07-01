package com.molerocn.deckly.data.repository

import com.molerocn.deckly.data.database.dao.CardDao
import com.molerocn.deckly.data.network.CardService
import com.molerocn.deckly.domain.model.Card
import com.molerocn.deckly.domain.model.toDomainModel
import javax.inject.Inject

class CardRepository @Inject constructor(
    private val api: CardService,
    private val cardDao: CardDao
) {

    suspend fun getAllCardsFromApi(): List<Card> {
        val response = api.getCards()
        return response.map { it.toDomainModel() }
    }

    suspend fun getAllCardsFromDatabase(): List<Card> {
        val response = cardDao.getCards()
        return response.map { it.toDomainModel() }
    }

    suspend fun clearCards() {
        cardDao.deleteAllCards()
    }
}