package com.molerocn.deckly.data.model

import com.google.gson.annotations.SerializedName

data class UserModel(
    @SerializedName("id") val id: String,
    @SerializedName("sub") val sub: String,
    @SerializedName("session_token") val sessionToken: String
)
