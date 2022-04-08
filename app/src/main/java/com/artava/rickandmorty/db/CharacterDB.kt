package com.artava.rickandmorty.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.artava.rickandmorty.model.Character

@Database(entities = arrayOf(Character::class), version = 1, exportSchema = false)
@TypeConverters(Character.LocationConverter::class, Character.ListConverter::class)

abstract class CharacterDB : RoomDatabase() {
    abstract fun characterDao(): CharacterDao

    companion object {

        @Volatile
        private var INSTANCE: CharacterDB? = null

        fun getDataseCharacter(context: Context): CharacterDB {
            if (INSTANCE != null) return INSTANCE!!
            synchronized(this) {
                INSTANCE = Room
                    .databaseBuilder(context, CharacterDB::class.java, "chdb")
                    .build()
                return INSTANCE!!

            }
        }
    }
}