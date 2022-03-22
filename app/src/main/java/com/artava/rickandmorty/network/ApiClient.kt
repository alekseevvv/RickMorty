package com.artava.rickandmorty.network

import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.CharacterList
import retrofit2.Response

class ApiClient(private val ramS: RandMService) {

    suspend fun getCharacterById(characterId: Int): Response<Character>{
        return ramS.getCharacterById(characterId)
    }

    suspend fun getAllCharacter(): Response<CharacterList>{
        return ramS.getAllCharacter()
    }
}
