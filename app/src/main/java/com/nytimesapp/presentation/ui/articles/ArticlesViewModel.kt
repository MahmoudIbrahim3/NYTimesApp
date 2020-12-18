package com.nytimesapp.presentation.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.Interactors
import com.nytimesapp.data.utils.DataResource
import com.nytimesapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val interactors: Interactors): BaseViewModel() {

    private val _mostPopularArticlesLiveData = MutableLiveData<DataResource<List<ArticleEntity>>>()

    val mostPopularArticlesLiveData: LiveData<DataResource<List<ArticleEntity>>> =
        Transformations.switchMap(_mostPopularArticlesLiveData) {
            loadMostPopularArticles(1)
        }

    private fun loadMostPopularArticles(period: Int) =
        interactors.getMostPopularArticles(period) as LiveData<DataResource<List<ArticleEntity>>>

    fun refresh() {
        _mostPopularArticlesLiveData.value.let {
            _mostPopularArticlesLiveData.value = it
        }
    }
}