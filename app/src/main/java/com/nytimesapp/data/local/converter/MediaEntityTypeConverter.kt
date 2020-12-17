package com.nytimesapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nytimes.entities.articles.MediaEntity

class MediaEntityTypeConverter {

    @TypeConverter
    fun toMediaEntity(value: String): MediaEntity =
        Gson().fromJson(value, MediaEntity::class.java)

    @TypeConverter
    fun fromMediaEntity(mediaEntity: MediaEntity): String =
        Gson().toJson(mediaEntity, MediaEntity::class.java)
}