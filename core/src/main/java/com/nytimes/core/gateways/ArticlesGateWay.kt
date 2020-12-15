package com.nytimes.core.gateways

import com.nytimes.entities.articles.ArticlesResponse

interface ArticlesGateWay {
    suspend fun getMostPopularArticles(): ArticlesResponse
}