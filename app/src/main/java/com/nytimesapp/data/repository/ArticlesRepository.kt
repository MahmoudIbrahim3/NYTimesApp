package com.nytimesapp.data.repository

import androidx.lifecycle.LiveData
import com.nytimes.core.gateways.ArticlesGateWay
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.local.dao.ArticleDao
import com.nytimes.entities.articles.MostPopularArticlesEntity
import com.nytimesapp.data.remote.ApiResponse
import com.nytimesapp.data.remote.api.ArticlesApi
import com.nytimesapp.data.utils.AppExecutors
import com.nytimesapp.data.utils.DataResource
import com.nytimesapp.data.utils.NetworkBoundResource
import com.nytimesapp.data.utils.RateLimiter
import com.nytimesapp.presentation.utils.AppConst
import java.util.concurrent.TimeUnit

class ArticlesRepository(private val appExecutors: AppExecutors,
                         private val articleDao: ArticleDao,
                         private val articlesApi: ArticlesApi
): ArticlesGateWay {

    private val articleListRateLimit = RateLimiter<String>(1, TimeUnit.MINUTES)

    override fun getMostPopularArticles(period: Int):
            LiveData<DataResource<List<ArticleEntity>>> {
        return object :
            NetworkBoundResource<List<ArticleEntity>, MostPopularArticlesEntity>(appExecutors) {

            override fun saveCallResult(item: MostPopularArticlesEntity) =
                item.results?.let { articleDao.insertMostPopularArticles(it) }

            override fun shouldFetch(data: List<ArticleEntity>?) =
                data == null || data.isEmpty() || articleListRateLimit.shouldFetch(data.toString())

            override fun loadFromDb() =
                articleDao.loadMostPopularArticles()

            override fun createCall(): LiveData<ApiResponse<MostPopularArticlesEntity>> =
                articlesApi.getMostPopularsArticles(1, AppConst.API_KEY)

        }.asLiveData()
    }
}