package com.nytimesapp.presentation.ui.articles

import android.os.Bundle
import androidx.core.widget.NestedScrollView
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.di.ViewModelFactory

import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.presentation.ui.articledetails.ArticleDetailActivity
import com.nytimesapp.presentation.ui.base.BaseActivity
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_articles_pane.*
import javax.inject.Inject

/**
 * An activity representing a list of Pings. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a [ArticleDetailActivity] representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 */
class ArticlesActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var adapter: ArticlesRecyclerViewAdapter

    @Inject
    lateinit var dispatchingSupportFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =
        dispatchingSupportFragmentInjector

    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)

        setContentView(R.layout.activity_articles)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        toolbar.title = title

        if (findViewById<NestedScrollView>(R.id.item_detail_container) != null) {
            twoPane = true
        }

        initViewModel()
        setupRecyclerView()
        initMostPopularArticlesLiveData()
        initSwipToRefresh()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticlesViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = ArticlesRecyclerViewAdapter(this, twoPane)
        rvArticles.adapter = adapter
    }

    private fun initMostPopularArticlesLiveData() {
        viewModel.mostPopularArticlesLiveData.observe(this, Observer {
            when(it) {
                is DataResource.Loading -> startLoading(swipeToRefresh)
                is DataResource.Success -> {
                    stopLoading(swipeToRefresh)
                    it.value?.results?.let { data -> renderData(data) }
                }
                is DataResource.Failure -> onLoadDataFailure(it.errorEntity)
            }
        })
    }

    private fun renderData(articles: List<ArticleEntity>) {
        adapter.addItems(articles)
        adapter.notifyDataSetChanged()
    }

    private fun initSwipToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            adapter.resetItems()
//            viewModel.getMostPopularArticles(1)
        }
    }
}