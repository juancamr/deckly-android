package com.molerocn.deckly.domain.model

import com.molerocn.deckly.data.database.entities.CardEntity
import com.molerocn.deckly.data.network.model.CardModelApi

data class Card(
    val back: String,
    val front: String
)

fun CardModelApi.toDomainModel() = Card(
    back = back,
    front = front
)

fun CardEntity.toDomainModel() = Card(
    back = back,
    front = front
)
