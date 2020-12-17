package com.nytimes.core.usecases

import com.nytimes.core.gateways.ArticlesGateWay

class GetMostPopularArticles(private val articlesGateWay: ArticlesGateWay) {
    operator fun invoke(period: Int) =
        articlesGateWay.getMostPopularArticles(period)
}