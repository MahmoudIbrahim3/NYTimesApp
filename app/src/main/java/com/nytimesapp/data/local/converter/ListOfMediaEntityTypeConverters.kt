package com.nytimesapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.nytimes.entities.articles.MediaEntity
import java.lang.reflect.Type

object ListOfMediaEntityTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromMediaEntitiesList(optionValues: List<MediaEntity?>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MediaEntity?>?>() {}.getType()
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    @JvmStatic
    fun toMediaEntitiesList(optionValuesString: String?): List<MediaEntity>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<MediaEntity?>?>() {}.getType()
        return gson.fromJson(optionValuesString, type)
    }
}
