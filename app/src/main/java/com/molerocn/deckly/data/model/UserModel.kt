package com.molerocn.deckly.data.model

import com.google.gson.annotations.SerializedName
import com.molerocn.deckly.presentation.model.UserModel as PresentationUserModel

data class UserModel(
    @SerializedName("user_id") val id: String,
    @SerializedName("email") val email: String,
    @SerializedName("name") val name: String,
    @SerializedName("picture") val picture: String
)

fun UserModel.toPresentationModel(): PresentationUserModel {
    return PresentationUserModel(
        email = email,
        name = name,
        picture = picture
    )
}