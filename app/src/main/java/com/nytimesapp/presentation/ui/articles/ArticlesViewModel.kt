package com.nytimesapp.presentation.ui.articles

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.data.Interactors
import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val interactors: Interactors): BaseViewModel() {

    val mostPopularArticlesLiveData = interactors.getMostPopularArticles(1)
            as LiveData<DataResource<List<ArticleEntity>>>
}