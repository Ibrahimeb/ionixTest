package com.example.ionixtest.domain.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverter
import com.google.gson.Gson

import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

@Entity(tableName = "movie")
data class MovieModel(
    val title: String,
    val image: String,
    @PrimaryKey
    val id: String,
    val releaseState: String,
    val plot: String,
    val stars: List<String>,
)

object Converters {
    @TypeConverter
    fun fromString(value: String): List<String> {
        val listType: Type = object : TypeToken<ArrayList<String>>() {}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: List<String>): String {
        val gson = Gson()
        return gson.toJson(list)
    }
}
