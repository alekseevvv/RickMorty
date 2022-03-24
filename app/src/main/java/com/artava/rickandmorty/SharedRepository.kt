package com.artava.rickandmorty

import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.CharacterList
import com.artava.rickandmorty.model.Episode
import com.artava.rickandmorty.model.EpisodeList
import com.artava.rickandmorty.network.RetrofitHelper

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character?{
        val request = RetrofitHelper.apiClient.getCharacterById(characterId)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getAllCharacter(): CharacterList?{
        val request = RetrofitHelper.apiClient.getAllCharacter()
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getCharacterByPage(page: Int): CharacterList?{
        val request = RetrofitHelper.apiClient.getCharacterByPage(page)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getCharacterByName(name: String): CharacterList?{
        val request = RetrofitHelper.apiClient.getCharacterByName(name)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }

    suspend fun getEpisodeByPage(page: Int): EpisodeList?{
        val request = RetrofitHelper.apiClient.getEpisodeByPage(page)
        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }
}