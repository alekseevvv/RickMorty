package com.artava.rickandmorty

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RandMService {

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<Character>
}