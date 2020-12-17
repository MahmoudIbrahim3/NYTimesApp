package com.nytimesapp.presentation.ui.articles

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.presentation.ui.articledetails.ArticleDetailActivity
import com.nytimesapp.presentation.ui.articledetails.ArticleDetailFragment
import com.nytimesapp.presentation.utils.AppConst

class ArticlesRecyclerViewAdapter(
    private val parentActivity: ArticlesActivity,
    private val twoPane: Boolean)
    : RecyclerView.Adapter<ArticlesRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener
    private var items = ArrayList<ArticleEntity>()

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as ArticleEntity
            if (twoPane) {
                val fragment = ArticleDetailFragment()
                    .apply {
                        arguments = Bundle().apply {
                            putString(
                                AppConst.INTENT_ITEM_ENTITY,
                                Gson().toJson(item, ArticleEntity::class.java)
                            )
                        }
                    }
                parentActivity.supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.item_detail_container, fragment)
                    .commit()
            } else {
                val intent = Intent(v.context, ArticleDetailActivity::class.java).apply {
                    putExtra(
                        AppConst.INTENT_ITEM_ENTITY,
                        Gson().toJson(item, ArticleEntity::class.java)
                    )
                }
                v.context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.activity_articles_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items[position]
        holder.tvTitle.text = item.title
        holder.tvDescription.text = item.abstract

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    override fun getItemCount() = items.size

    fun addItems(items: List<ArticleEntity>) {
        this.items.addAll(items)
    }

    fun getItems() = items

    fun resetItems() = items.clear()

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvTitle: TextView = view.findViewById(R.id.tvTitle)
        val tvDescription: TextView = view.findViewById(R.id.tvDescription)
    }
}