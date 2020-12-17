package com.nytimesapp.data.local.dao

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.local.AppDatabaseTest
import com.nytimesapp.utils.LiveDataTestUtil.getValue
import org.junit.Test

import org.junit.Assert.*
import org.junit.Rule
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticleDaoTest: AppDatabaseTest() {

    @get:Rule
    val instantTaskExecutorRule = InstantTaskExecutorRule()

    @Test
    fun insertAndLoadMostPopularArticles() {
        val articleEntity1 = ArticleEntity(id = "1")
        val articleEntity2 = ArticleEntity(id = "2")
        val articlesList = arrayListOf(articleEntity1, articleEntity2)

        appDatabase.articleDao()?.insertMostPopularArticles(articlesList)

        val articles = appDatabase.articleDao()?.loadMostPopularArticles()?.let { getValue(it) }
        assertEquals(articles?.size, 2)
    }

    @Test
    fun loadMostPopularArticles() {

    }
}