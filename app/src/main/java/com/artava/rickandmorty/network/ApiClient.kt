package com.artava.rickandmorty.network

import com.artava.rickandmorty.model.Character
import com.artava.rickandmorty.model.CharacterList
import com.artava.rickandmorty.model.Episode
import com.artava.rickandmorty.model.EpisodeList
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
}
