package com.molerocn.deckly.domain.model

import com.molerocn.deckly.data.database.entities.CardEntity
import com.molerocn.deckly.data.model.CardModel

data class Card(
    val back: String,
    val front: String
)

fun CardModel.toDomainModel() = Card(
    back = back,
    front = front
)

fun CardEntity.toDomainModel() = Card(
    back = back,
    front = front
)
