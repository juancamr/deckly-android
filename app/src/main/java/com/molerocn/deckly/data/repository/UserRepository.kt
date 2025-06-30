package com.molerocn.deckly.data.repository

import com.molerocn.deckly.core.AuthHelper
import com.molerocn.deckly.domain.model.UserModel
import com.molerocn.deckly.data.model.toDomainModel
import com.molerocn.deckly.data.network.UserService
import jakarta.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService,
    private val authHelper: AuthHelper
) {

    suspend fun signInWithGoogle(token: String): UserModel? {
        val response = api.signInWithGoogle(token)
        response?.let {
            authHelper.signIn(mapOf(
                "name" to it.name.substringBefore(" "),
                "email" to it.email,
                "picture" to it.picture
            ))
        }
        return response?.toDomainModel()
    }
}