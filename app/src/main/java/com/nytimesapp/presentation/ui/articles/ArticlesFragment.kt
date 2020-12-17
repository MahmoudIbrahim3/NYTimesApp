package com.nytimesapp.presentation.ui.articles

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.di.ViewModelFactory

import com.nytimesapp.errorhandling.DataResource
import com.nytimesapp.presentation.ui.base.BaseFragment
import com.nytimesapp.utils.OpenForTesting
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.activity_articles_pane.*
import kotlinx.android.synthetic.main.layout_screen_loading.*
import javax.inject.Inject

@OpenForTesting
class ArticlesFragment : BaseFragment() {

    private var isTablet: Boolean = false

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ArticlesViewModel
    private lateinit var adapter: ArticlesRecyclerViewAdapter

    // Whether or not the activity is in two-pane mode, i.e. running on a tablet device.
    private var twoPane: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        lateinit var view: View

        isTablet = requireContext().resources.getBoolean(R.bool.isTablet)

        when {
            isTablet -> {
                view = inflater.inflate(R.layout.fragment_articles_land, container, false)
//                displayMasterDetailLayout(view)
            }
            else -> {
                view = inflater.inflate(R.layout.fragment_articles, container, false)
//                displaySingleLayout(view)
            }
        }

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        setupRecyclerView()
        initMostPopularArticlesLiveData()
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticlesViewModel::class.java)
    }

    private fun setupRecyclerView() {
        adapter = ArticlesRecyclerViewAdapter(isTablet)
        rvArticles.adapter = adapter
    }

    private fun initMostPopularArticlesLiveData() {
        viewModel.mostPopularArticlesLiveData.observe(viewLifecycleOwner, Observer {
            when(it) {
                is DataResource.Loading -> startLoading(pbLoading)
                is DataResource.Success -> {
                    stopLoading(pbLoading)
                    renderData(it.value)
                }
                is DataResource.Failure -> onLoadDataFailure(it.errorEntity)
            }
        })
    }

    private fun renderData(articles: List<ArticleEntity>) {
        adapter.addItems(articles)
        adapter.notifyDataSetChanged()
    }
}