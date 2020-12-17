package com.nytimesapp.data.local.converter

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.nytimes.entities.articles.MediaMetaDataEntity

class MediaMetaDataEntityTypeConverter {

    @TypeConverter
    fun toMediaMetaDataEntity(value: String): MediaMetaDataEntity =
        Gson().fromJson(value, MediaMetaDataEntity::class.java)

    @TypeConverter
    fun fromMediaMetaDataEntity(mediaMetaDataEntity: MediaMetaDataEntity): String =
        Gson().toJson(mediaMetaDataEntity, MediaMetaDataEntity::class.java)
}