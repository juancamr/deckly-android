package com.molerocn.deckly.data.repository

import com.molerocn.deckly.data.database.dao.DeckDao
import com.molerocn.deckly.domain.model.Deck
import com.molerocn.deckly.domain.model.toDomainModel
import javax.inject.Inject

class DeckRepository @Inject constructor(
    private val deckDao: DeckDao
) {

    suspend fun getAllDecksFromDatabase(): List<Deck> {
        val response = deckDao.getDecks()
        return response.map { it.toDomainModel() }
    }

}