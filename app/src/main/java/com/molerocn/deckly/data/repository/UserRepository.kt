package com.molerocn.deckly.data.repository

import com.molerocn.deckly.domain.model.UserModel
import com.molerocn.deckly.data.model.toDomainModel
import com.molerocn.deckly.data.network.UserService
import jakarta.inject.Inject

class UserRepository @Inject constructor(
    private val api: UserService
) {

    suspend fun signInWithGoogle(token: String): UserModel? {
        val response = api.signInWithGoogle(token)
        response?.let {
            UserProvider.userSub = it.name.substringBefore(" ")
            UserProvider.userEmail = it.email
            UserProvider.userPicture = it.picture
        }
        return response?.toDomainModel()
    }
}