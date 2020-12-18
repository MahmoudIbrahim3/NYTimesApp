package com.nytimesapp.presentation.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.di.ViewModelFactory

import com.nytimesapp.data.utils.DataResource
import com.nytimesapp.presentation.MainActivity
import com.nytimesapp.presentation.ui.base.BaseFragment
import com.nytimesapp.presentation.utils.AppConst
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_articles.*
import javax.inject.Inject

class ArticlesFragment : BaseFragment() {

    private var twoPane: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var adapter: ArticlesRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        twoPane = requireContext().resources.getBoolean(R.bool.isTablet)

        return when {
            twoPane ->
                inflater.inflate(R.layout.fragment_articles_land, container, false)
            else ->
                inflater.inflate(R.layout.fragment_articles, container, false)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        setupRecyclerView()
        initMostPopularArticlesLiveData()
        initOnArticleClickLiveData()
        initSwipeToRefresh()
        viewModel.refresh()
    }

    override fun onStart() {
        super.onStart()
        (activity as MainActivity).setActionBarTitle(getString(R.string.most_populars), false)
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticlesViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = ArticlesRecyclerViewAdapter(twoPane)
        rvArticles.adapter = adapter
    }

    private fun initMostPopularArticlesLiveData() {
        viewModel.mostPopularArticlesLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is DataResource.Loading ->
                    startLoading(swipeToRefresh)
                is DataResource.Success -> {
                    stopLoading(swipeToRefresh)
                    renderData(it.value)
                }
                is DataResource.Failure -> {
                    stopLoading(swipeToRefresh)
                    onLoadDataFailure(it.errorMsg)
                }
            }
        })
    }

    private fun initOnArticleClickLiveData() {
        adapter.onArticleClickedLiveData.observe(viewLifecycleOwner, Observer {
            val arg = Bundle()
            arg.putString(AppConst.INTENT_ITEM_ENTITY, Gson().toJson(it, ArticleEntity::class.java))
            if (twoPane) {
                val navHostFragment = childFragmentManager.findFragmentById(
                    R.id.article_nav_container
                ) as NavHostFragment
                navHostFragment.navController.navigate(R.id.articleDetailFragment, arg)
            } else {
                findNavController().navigate(
                    R.id.action_articlesFragment_to_articleDetailActivity, arg
                )
            }
        })
    }

    private fun renderData(articles: List<ArticleEntity>) {
        adapter.resetItems()
        adapter.addItems(articles)
        adapter.notifyDataSetChanged()
    }

    private fun initSwipeToRefresh() {
        swipeToRefresh.setOnRefreshListener {
            adapter.resetItems()
            viewModel.refresh()
        }
    }
}