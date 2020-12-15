package com.nytimesapp.presentation.utils

import android.util.Log

abstract class EndlessRecyclerOnScrollListener(private val mLinearLayoutManager: androidx.recyclerview.widget.LinearLayoutManager)
    : androidx.recyclerview.widget.RecyclerView.OnScrollListener() {

    private var previousTotal = 0 // The total number of items in the dataset after the last load
    private var loading = true // True if we are still waiting for the last set of data to load.
    private val visibleThreshold = 2 // The minimum amount of items to have below your current scroll position before loading more.
    private var firstVisibleItem: Int = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    private var current_page = 1

    fun resetParams() {
        previousTotal = 0
        loading = true
        visibleItemCount = 2
        firstVisibleItem = 0
        visibleItemCount = 0
        totalItemCount = 0
        current_page = 1
    }

    override fun onScrolled(recyclerView: androidx.recyclerview.widget.RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)

        Log.e("InvoicesTabFragment", "" + current_page);

        visibleItemCount = recyclerView!!.childCount
        totalItemCount = mLinearLayoutManager.itemCount
        firstVisibleItem = mLinearLayoutManager.findFirstVisibleItemPosition()

        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && totalItemCount - visibleItemCount <= firstVisibleItem + visibleThreshold) {
            // End has been reached

            // Do something
            current_page++

            onLoadMore(current_page)

            loading = true
        }
    }

    abstract fun onLoadMore(current_page: Int)

    companion object {
        var TAG = EndlessRecyclerOnScrollListener::class.java.simpleName
    }
}