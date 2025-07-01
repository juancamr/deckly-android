package com.molerocn.deckly.domain.model

import com.molerocn.deckly.data.model.UserModel

data class User(
    val name: String
)

fun UserModel.toDomainModel(): User {
    return User(
        name = name.substringBefore(" ")
    )
}
