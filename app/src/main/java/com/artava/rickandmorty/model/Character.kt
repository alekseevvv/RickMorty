package com.artava.rickandmorty.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "created")
    val created: String?,
    @ColumnInfo(name = "episode")
    val episode: List<String>?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "location")
    val location: Location?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val full_name: String?,
    @ColumnInfo(name = "origin")
    val origin: Origin?,
    @ColumnInfo(name = "species")
    val species: String?,
    @ColumnInfo(name = "status")
    val status: String?,
    @ColumnInfo(name = "type")
    val type: String?,
    @ColumnInfo(name = "url")
    val url: String?
) {

    data class Location(
        val name: String?,
        val url: String?
    )

    data class Origin(
        val name: String?,
        val url: String?
    )
}