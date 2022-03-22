package com.artava.rickandmorty.model

import com.google.gson.annotations.SerializedName

data class Character(
    val created: String?,
    val episode: List<String>?,
    val gender: String?,
    val id: Int?,
    val image: String?,
    val location: Location?,
    @SerializedName("name")
    val full_name: String?,
    val origin: Origin?,
    val species: String?,
    val status: String?,
    val type: String?,
    val url: String?
) {
    data class ListCharacter(
        val listCharacter: List<Character>
    )

    data class Location(
        val name: String?,
        val url: String?
    )

    data class Origin(
        val name: String?,
        val url: String?
    )
}