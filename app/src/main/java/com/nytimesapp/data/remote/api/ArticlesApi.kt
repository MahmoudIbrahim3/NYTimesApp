package com.nytimesapp.data.remote.api

import androidx.lifecycle.LiveData
import com.nytimes.entities.articles.MostPopularArticlesEntity
import com.nytimesapp.data.remote.ApiFactory
import com.nytimesapp.data.remote.ApiResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ArticlesApi {
    @GET(ApiFactory.MOST_POPULAR_ARTICLES)
    fun getMostPopularsArticles(
        @Path("period") period: Int,
        @Query("api-key") apiKey: String
    ): LiveData<ApiResponse<MostPopularArticlesEntity>>
}
