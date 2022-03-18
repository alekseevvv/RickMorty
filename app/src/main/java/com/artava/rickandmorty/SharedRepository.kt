package com.artava.rickandmorty

import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.network.RetrofitHelper

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character?{
        val request = RetrofitHelper.apiClient.getCharacterById(characterId)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllCharacter(): List<Character>?{
        val request = RetrofitHelper.apiClient.getAllCharacter()
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }
}