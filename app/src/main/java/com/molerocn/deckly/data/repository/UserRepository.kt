package com.molerocn.deckly.data.repository

import com.molerocn.deckly.data.model.UserModel
import com.molerocn.deckly.data.network.UserService
import jakarta.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService
) {

    suspend fun signInWithGoogle(token: String): UserModel? {
        val response = api.signInWithGoogle(token)
        return response
    }
}