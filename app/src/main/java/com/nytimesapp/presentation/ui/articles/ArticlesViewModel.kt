package com.nytimesapp.presentation.ui.articles

import androidx.lifecycle.LiveData
import com.nytimes.entities.articles.MostPopularArticlesEntity
import com.nytimesapp.data.Interactors
import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.presentation.ui.base.BaseViewModel
import javax.inject.Inject

class ArticlesViewModel @Inject constructor(private val interactors: Interactors): BaseViewModel() {

    val mostPopularArticlesLiveData: LiveData<DataResource<MostPopularArticlesEntity>> =
        interactors.getMostPopularArticles(1) as LiveData<DataResource<MostPopularArticlesEntity>>

//    val mostPopularArticlesLiveData = MutableLiveData<DataResource<MostPopularArticlesEntity?>>()
//
//    fun getMostPopularArticles(period: Int) {
//        viewModelScope.launch {
//            mostPopularArticlesLiveData.value = interactors.getMostPopularArticles(period) as
//                    DataResource<MostPopularArticlesEntity?>
//        }
//    }
}