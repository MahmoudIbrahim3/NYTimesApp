package com.nytimesapp.data.repository

import androidx.lifecycle.LiveData
import com.nytimes.core.gateways.ArticlesGateWay
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.local.dao.ArticleDao
import com.nytimes.entities.articles.MostPopularArticlesEntity
import com.nytimesapp.data.remote.api.ApiResponse
import com.nytimesapp.data.remote.api.ArticlesApi
import com.nytimesapp.errorhandling.AppExecutors
import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.errorhandling.NetworkBoundResource
import com.nytimesapp.presentation.utils.AppConst

class ArticlesRepository(private val appExecutors: AppExecutors,
                         private val articleDao: ArticleDao,
                         private val articlesApi: ArticlesApi
): ArticlesGateWay {

    override fun getMostPopularArticles(period: Int):
            LiveData<DataResource<List<ArticleEntity>>> {
        return object : NetworkBoundResource<List<ArticleEntity>, MostPopularArticlesEntity>(appExecutors) {

            override fun saveCallResult(item: MostPopularArticlesEntity) =
                item.results?.let { articleDao.insertMostPopularArticles(it) }

            override fun shouldFetch(data: List<ArticleEntity>?) =
                data == null || data.isEmpty()

            override fun loadFromDb() =
                articleDao.loadMostPopularArticles()

            override fun createCall(): LiveData<ApiResponse<MostPopularArticlesEntity>> =
                articlesApi.getMostPopularsArticles(1, AppConst.API_KEY)

        }.asLiveData()
    }
}