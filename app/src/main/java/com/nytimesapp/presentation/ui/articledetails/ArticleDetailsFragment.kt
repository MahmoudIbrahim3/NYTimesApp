package com.nytimesapp.presentation.ui.articledetails

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.di.ViewModelFactory
import com.nytimesapp.presentation.MainActivity
import com.nytimesapp.presentation.ui.base.BaseFragment
import com.nytimesapp.presentation.utils.AppConst
import dagger.android.support.AndroidSupportInjection
import kotlinx.android.synthetic.main.fragment_articles_item.*
import javax.inject.Inject

class ArticleDetailsFragment : BaseFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var viewModel: ArticleDetailsViewModel
    private var item: ArticleEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidSupportInjection.inject(this)

        arguments?.let {
            if (it.containsKey(AppConst.INTENT_ITEM_ENTITY)) {
                item = Gson().fromJson<ArticleEntity>(
                    it.getString(AppConst.INTENT_ITEM_ENTITY),
                    ArticleEntity::class.java
                )
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.activity_article_detail, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        initViewModel()
        fillDataOnUI()
    }

    override fun onStart() {
        super.onStart()
        if(requireContext().resources.getBoolean(R.bool.isTwoPane))
            (activity as MainActivity).setActionBarTitle(item?.section, false)
        else
            (activity as MainActivity).setActionBarTitle(item?.section, true)
    }

    private fun fillDataOnUI() {
        tvTitle.text = item?.title
        tvDescription.text = item?.abstract
    }

    private fun initViewModel() {
        viewModel = ViewModelProvider(this, viewModelFactory)
            .get(ArticleDetailsViewModel::class.java)
    }
}