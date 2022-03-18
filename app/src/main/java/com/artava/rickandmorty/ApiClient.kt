package com.artava.rickandmorty

import retrofit2.Response

class ApiClient(private val ramS: RandMService) {
    suspend fun getCharacterById(characterId: Int): Response<Character>{
        return ramS.getCharacterById(characterId)
    }
}
