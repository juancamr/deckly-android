package com.molerocn.deckly.domain.model

import com.molerocn.deckly.data.database.entities.DeckEntity

data class Deck(
    val name: String
)

fun DeckEntity.toDomainModel() = Deck(
    name = name
)