package com.juancamr.deckly.core

import com.juancamr.deckly.BuildConfig
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {
    fun getRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.BACKEND_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}