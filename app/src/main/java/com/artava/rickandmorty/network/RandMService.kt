package com.artava.rickandmorty.network

import com.artava.rickandmorty.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RandMService {

    @GET("character/{id}")
    suspend fun getCharacterById(
        @Path("id") characterId: Int
    ): Response<Character>

    @GET("character")
    suspend fun getAllCharacter(): Response<CharacterList>

    @GET("character/")
    suspend fun getCharacterByPage(
        @Query("page") page: Int
    ): Response<CharacterList>

    @GET("character/")
    suspend fun getCharacterByName(
        @Query("name") name: String
    ): Response<CharacterList>

    @GET("episode/")
    suspend fun getEpisodeByPage(
        @Query("page") page: Int
    ): Response<EpisodeList>

    @GET("location/")
    suspend fun getLocationByPage(
        @Query("page") page: Int
    ): Response<LocationList>
}