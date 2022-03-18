package com.artava.rickandmorty

class SharedRepository {
    suspend fun getCharacterById(characterId: Int): Character?{
        val request = RetrofitHelper.apiClient.getCharacterById(characterId)

        if (request.isSuccessful){
            return request.body()!!
        }
        return null
    }
}