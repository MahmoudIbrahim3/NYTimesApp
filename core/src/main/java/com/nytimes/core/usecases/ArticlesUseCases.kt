package com.nytimes.core.usecases

import com.nytimes.core.gateways.ArticlesGateWay

class GetMostPopularArticles(private val articlesGateWay: ArticlesGateWay) {
    suspend fun invoke() = articlesGateWay.getMostPopularArticles()
}