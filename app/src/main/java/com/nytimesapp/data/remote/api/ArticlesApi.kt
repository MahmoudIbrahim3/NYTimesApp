package com.nytimesapp.data.remote.api

import com.nytimes.entities.articles.ArticlesResponse
import com.nytimesapp.data.remote.ApiFactory
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticlesApi {
    @GET(ApiFactory.MOST_POPULAR_ARTICLES)
    suspend fun getMostPopularsArticles(
        @Query("api-key") apiKey: String
    ): ArticlesResponse
}
