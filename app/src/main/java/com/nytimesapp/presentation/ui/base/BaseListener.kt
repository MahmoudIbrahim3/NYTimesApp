package com.nytimesapp.presentation.ui.base

import android.view.View

interface BaseListener {
    fun startLoading(viewLoader: View)
    fun stopLoading(viewLoader: View)
    fun onLoadDataFailure(error: String)
    fun showSnackBar(msg: String)
    fun showShortToast(msg: String)
    fun showLongToast(msg: String)
    fun showAlertDialog(msg: String)
}