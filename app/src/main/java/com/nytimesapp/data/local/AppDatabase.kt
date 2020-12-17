package com.nytimesapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.local.converter.*
import com.nytimesapp.data.local.dao.ArticleDao

@Database(entities = [
    ArticleEntity::class
], version = 1, exportSchema = false)
@TypeConverters(
    ListOfMediaEntityTypeConverters::class,
    ListOfStringsTypeConverters::class,
    MediaEntityTypeConverter::class,
    MediaMetaDataEntityTypeConverter::class
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun articleDao(): ArticleDao?
}