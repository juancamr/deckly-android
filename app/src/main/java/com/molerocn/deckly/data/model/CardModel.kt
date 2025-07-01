package com.molerocn.deckly.data.model

import com.google.gson.annotations.SerializedName

data class CardModel(
    @SerializedName("front") val front: String,
    @SerializedName("back") val back: String
)