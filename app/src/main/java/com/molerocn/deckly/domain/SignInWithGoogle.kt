package com.molerocn.deckly.domain

import com.molerocn.deckly.data.model.UserModel
import com.molerocn.deckly.data.repository.UserRepository
import javax.inject.Inject

class SignInWithGoogle @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(token: String): UserModel? = repository.signInWithGoogle(token)
}
