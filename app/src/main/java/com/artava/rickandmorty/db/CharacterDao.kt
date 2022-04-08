package com.artava.rickandmorty.db

import androidx.room.*
import com.artava.rickandmorty.model.Character
import kotlinx.coroutines.flow.Flow

@Dao
interface CharacterDao{
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addCharacter(character: Character){
    }

   /* @Query("SELECT * FROM characters ORDER BY name DESC")
    fun getCharacter(): Flow<List<Character>>

    @Delete
    suspend fun deleteCharacter(character: Character)*/
}