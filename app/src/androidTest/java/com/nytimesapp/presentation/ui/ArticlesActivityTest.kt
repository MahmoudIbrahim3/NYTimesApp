package com.nytimesapp.presentation.ui

import androidx.lifecycle.MutableLiveData
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.intent.rule.IntentsTestRule
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.presentation.ui.articles.ArticlesActivity
import com.nytimesapp.presentation.ui.articles.ArticlesViewModel
import com.nytimesapp.utils.RecyclerViewMatcher
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.ArgumentMatchers
import org.mockito.Mockito


@RunWith(AndroidJUnit4::class)
class ArticlesActivityTest {

    private lateinit var viewModel: ArticlesViewModel
    private val articlesLiveData = MutableLiveData<DataResource<List<ArticleEntity>>>()

    @Rule
    @JvmField
    var addTraderIntentTestRule: IntentsTestRule<ArticlesActivity> = IntentsTestRule(ArticlesActivity::class.java)


    @Before
    fun init() {
        viewModel = Mockito.mock(ArticlesViewModel::class.java)
        Mockito.`when`(viewModel.mostPopularArticlesLiveData).thenReturn(articlesLiveData)
    }

    @Test
    fun testLoading() {

        articlesLiveData.postValue(DataResource.Loading)
//        onView(withId(R.id.pbLoading)).check(matches(isDisplayed()))
    }


    @Test
    fun testArticlesLoaded() {
        val articleEntity1 =
            ArticleEntity(id = "1", title = "Article number 1", abstract = "Abstract number 1")
        val articleEntity2 =
            ArticleEntity(id = "2", title = "Article number 2", abstract = "Abstract number 2")
        val articles = arrayListOf(articleEntity1, articleEntity2)

        articlesLiveData.postValue(DataResource.Success(articles))

        Thread.sleep(3000)
        onView(withRecyclerView(R.id.rvArticles).atPosition(0))
            .check(matches(hasDescendant(withText("Article number 1"))));
    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun listMatcher(): RecyclerViewMatcher {
        return RecyclerViewMatcher(R.id.rvArticles)
    }
}