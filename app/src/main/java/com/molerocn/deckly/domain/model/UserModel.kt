package com.molerocn.deckly.domain.model

import com.molerocn.deckly.presentation.model.UserModel as PresentationUserModel

data class UserModel(
    val name: String
)

fun UserModel.toPresentationModel(): PresentationUserModel {
    return PresentationUserModel(
        name = name.substringAfter(" ")
    )
}