package com.nytimesapp.data.repository

import com.nytimes.core.gateways.ArticlesGateWay
import com.nytimesapp.data.remote.api.ArticlesApi
import com.nytimesapp.presentation.utils.AppConst

class ArticlesRepository(private val articlesApi: ArticlesApi): ArticlesGateWay {
    override suspend fun getMostPopularArticles() =
        articlesApi.getMostPopularsArticles(AppConst.API_KEY)
}