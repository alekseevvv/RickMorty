package com.artava.rickandmorty.network

import com.artava.rickandmorty.model.*
import retrofit2.Response

class ApiClient(private val ramS: RandMService) {

    suspend fun getCharacterById(characterId: Int): Response<Character>{
        return ramS.getCharacterById(characterId)
    }

    suspend fun getAllCharacter(): Response<CharacterList>{
        return ramS.getAllCharacter()
    }

    suspend fun getCharacterByPage(page: Int): Response<CharacterList>{
        return ramS.getCharacterByPage(page)
    }

    suspend fun getCharacterByName(name: String): Response<CharacterList>{
        return ramS.getCharacterByName(name)
    }

    suspend fun getEpisodeByPage(page: Int): Response<EpisodeList>{
        return ramS.getEpisodeByPage(page)
    }

    suspend fun getEpisodeByName(name: List<Int>): Response<List<Episode>>{
        println(name)
        return ramS.getEpisodeByName(name)
    }

    suspend fun getLocationByPage(page: Int): Response<LocationList>{
        return ramS.getLocationByPage(page)
    }
}
