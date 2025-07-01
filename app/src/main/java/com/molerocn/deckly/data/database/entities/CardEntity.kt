package com.molerocn.deckly.data.database.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.molerocn.deckly.domain.model.Card

@Entity(tableName = "card_table")
data class CardEntity(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int = 0,
    @ColumnInfo(name = "front") val front: String,
    @ColumnInfo(name = "back") val back: String
)

fun Card.toDatabase() = CardEntity(
    front = front,
    back = back
)