package com.artava.rickandmorty.repository

import android.content.Context
import com.artava.rickandmorty.db.CharacterDB
import com.artava.rickandmorty.db.CharacterDao
import com.artava.rickandmorty.model.*
import com.artava.rickandmorty.network.RetrofitHelper

class SharedRepository {

    suspend fun insertCharacter(character: Character, chDao: CharacterDao) {
            return chDao.addCharacter(character)
        }

    suspend fun getCharacter(character: Character, chDao: CharacterDao) {
        return chDao.addCharacter(character)
    }

    suspend fun getCharacterById(characterId: Int): Character? {
        val request = RetrofitHelper.apiClient.getCharacterById(characterId)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getAllCharacter(): CharacterList? {
        val request = RetrofitHelper.apiClient.getAllCharacter()
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getCharacterByPage(page: Int): CharacterList? {
        val request = RetrofitHelper.apiClient.getCharacterByPage(page)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getCharacterByName(name: String): CharacterList? {
        val request = RetrofitHelper.apiClient.getCharacterByName(name)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getEpisodeByPage(page: Int): EpisodeList? {
        val request = RetrofitHelper.apiClient.getEpisodeByPage(page)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getEpiscodeByCharacter(name: List<Int>): List<Episode>? {
        val request = RetrofitHelper.apiClient.getEpisodeByName(name)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }

    suspend fun getLocationByPage(page: Int): LocationList? {
        val request = RetrofitHelper.apiClient.getLocationByPage(page)
        if (request.isSuccessful) {
            return request.body()!!
        }
        return null
    }
}