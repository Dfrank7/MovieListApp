package com.francis.moviestest.home.model.domain

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.*

class GenreTypeConverter {
    private val gson = Gson()
    @TypeConverter
    fun stringToList(data: String?): List<Genre> {
        if (data == null) {
            return Collections.emptyList()
        }

        val listType = object : TypeToken<List<Genre>>(){

        }.type

        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun listToString(someObjects: List<Int>): String {
        return gson.toJson(someObjects)
    }

    data class Genre(val genre_ids: Int)
}