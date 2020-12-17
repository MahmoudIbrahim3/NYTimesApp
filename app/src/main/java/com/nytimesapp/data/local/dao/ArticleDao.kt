package com.nytimesapp.data.local.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.nytimes.entities.articles.ArticleEntity

@Dao
abstract class ArticleDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    abstract fun insertMostPopularArticles(articles: List<ArticleEntity>)

    @Query("SELECT * FROM `ArticleEntity`")
    abstract fun loadMostPopularArticles(): LiveData<List<ArticleEntity>>
}