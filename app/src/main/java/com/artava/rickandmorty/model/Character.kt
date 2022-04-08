package com.artava.rickandmorty.model

import androidx.room.*
import com.google.gson.Gson
import com.google.gson.annotations.SerializedName
import com.google.gson.reflect.TypeToken
import java.util.stream.Collectors

@Entity(tableName = "characters")
data class Character(
    @PrimaryKey(autoGenerate = false)
    val id: Int?,
    @ColumnInfo(name = "created")
    val created: String?,
    @ColumnInfo(name = "episode")
    @TypeConverters(value = [ListConverter::class])
    val episode: List<String>?,
    @ColumnInfo(name = "gender")
    val gender: String?,
    @ColumnInfo(name = "image")
    val image: String?,
    @ColumnInfo(name = "location")
    @TypeConverters(value = [LocationConverter::class])
    val location: Location?,
    @ColumnInfo(name = "name")
    @SerializedName("name")
    val full_name: String?,
    @ColumnInfo(name = "origin")
    @TypeConverters(value = [LocationConverter::class])
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
    class ListConverter {
        companion object {
            @TypeConverter
            @JvmStatic
            fun fromString(value: String?): List<String> {
                val listType = object :
                    TypeToken<ArrayList<String?>?>() {}.type
                return Gson().fromJson(value, listType)
            }

            @TypeConverter
            @JvmStatic
            fun fromList(list: List<String?>?): String {
                val gson = Gson()
                return gson.toJson(list)
            }
        }
    }

    class LocationConverter {
        companion object {
            @TypeConverter
            @JvmStatic
            fun toLocation(value: String?): Location? {
                val listType = object :
                    TypeToken<ArrayList<String?>?>() {}.type
                return Gson().fromJson(value, listType)
            }

            @TypeConverter
            @JvmStatic
            fun fromLocation(loc: Character.Location?): String? {
                val nam = loc?.name
                return nam
            }

            @TypeConverter
            @JvmStatic
            fun toOrigin(value: String?): Origin? {
                val listType = object :
                    TypeToken<ArrayList<String?>?>() {}.type
                return Gson().fromJson(value, listType)
            }

            @TypeConverter
            @JvmStatic
            fun fromOrigin(org: Character.Origin?): String? {
                val nam = org?.name
                return nam
            }
        }
    }

    data class Location(
        val name: String?,
        val url: String?
    )

    data class Origin(
        val name: String?,
        val url: String?
    )
}