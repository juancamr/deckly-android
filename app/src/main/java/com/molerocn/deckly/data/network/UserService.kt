package com.molerocn.deckly.data.network

import com.molerocn.deckly.data.model.UserModel
import jakarta.inject.Inject
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class UserService @Inject constructor(
    private val api: UserApiClient
) {

    suspend fun signInWithGoogle(token: String): UserModel? {
        return withContext(Dispatchers.IO) {
            val body = mapOf("token" to token)
            val response = api.signInWithGoogle(body)
            response.body()
        }
    }
}