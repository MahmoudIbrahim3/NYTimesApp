package com.nytimesapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object ListOfStringsTypeConverters {
    @TypeConverter
    @JvmStatic
    fun fromStringsList(optionValues: List<String>?): String? {
        if (optionValues == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.getType()
        return gson.toJson(optionValues, type)
    }

    @TypeConverter
    @JvmStatic
    fun toStringsList(optionValuesString: String?): List<String>? {
        if (optionValuesString == null) {
            return null
        }
        val gson = Gson()
        val type: Type = object : TypeToken<List<String>?>() {}.getType()
        return gson.fromJson(optionValuesString, type)
    }
}
