package com.molerocn.deckly.data.network

import com.molerocn.deckly.data.model.UserModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApiClient {
    
    @POST("/auth/google")
    suspend fun signInWithGoogle(@Body body: Map<String, String>): Response<UserModel>
}
