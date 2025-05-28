package com.molerocn.deckly.data.network

import android.util.Log
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
            Log.i("signin", "el token es $token")
            val response = api.signInWithGoogle(body)
            response.body()
        }
    }
}