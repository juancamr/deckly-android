package com.molerocn.deckly.domain

import com.molerocn.deckly.presentation.model.UserModel
import com.molerocn.deckly.data.repository.UserRepository
import com.molerocn.deckly.domain.model.toPresentationModel
import javax.inject.Inject

class SignInWithGoogleUseCase @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(token: String): UserModel? =
        repository.signInWithGoogle(token)?.toPresentationModel()
}
