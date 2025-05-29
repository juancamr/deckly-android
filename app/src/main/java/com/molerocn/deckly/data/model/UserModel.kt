package com.molerocn.deckly.data.model

import com.google.gson.annotations.SerializedName
import com.molerocn.deckly.domain.model.UserModel as DomainUserModel

data class UserModel(
    @SerializedName("user_id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String
)

fun UserModel.toDomainModel(): DomainUserModel {
    return DomainUserModel(
        name = name.substringAfter(" ")
    )
}