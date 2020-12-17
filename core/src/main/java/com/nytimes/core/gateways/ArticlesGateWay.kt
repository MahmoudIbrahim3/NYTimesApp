package com.nytimes.core.gateways

interface ArticlesGateWay {
    fun getMostPopularArticles(period: Int): Any
}