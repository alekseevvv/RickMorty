package com.artava.rickandmorty

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitHelper {

    val baseUrl = "https://rickandmortyapi.com/api/"

    val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
    val ramS : RandMService by lazy {
        retrofit.create(RandMService::class.java)
    }
    val apiClient = ApiClient(ramS)
}