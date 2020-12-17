package com.nytimesapp.presentation.ui.articledetails

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.google.android.material.appbar.CollapsingToolbarLayout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.gson.Gson
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.presentation.utils.AppConst

/**
 * A fragment representing a single Item detail screen.
 * This fragment is either contained in a [ArticlesActivity]
 * in two-pane mode (on tablets) or a [ArticleDetailActivity]
 * on handsets.
 */
class ArticleDetailFragment : Fragment() {

    private var item: ArticleEntity? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(AppConst.INTENT_ITEM_ENTITY)) {
                item = Gson().fromJson<ArticleEntity>(
                    it.getString(AppConst.INTENT_ITEM_ENTITY),
                    ArticleEntity::class.java
                )

                activity?.findViewById<CollapsingToolbarLayout>(R.id.toolbar_layout)?.title =
                    item?.title
            }
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.item_detail, container, false)

        item?.let {
            rootView.findViewById<TextView>(R.id.item_detail).text = it.abstract
        }

        return rootView
    }
}