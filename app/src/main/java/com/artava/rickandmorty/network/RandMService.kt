package com.artava.rickandmorty.network

import com.artava.rickandmorty.model.Character
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface RandMService {

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<Character>

    @GET("character")
    suspend fun getAllCharacter(): Response<List<Character>>
}