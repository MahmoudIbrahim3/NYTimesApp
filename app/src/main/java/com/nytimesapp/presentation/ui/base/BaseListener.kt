package com.nytimesapp.presentation.ui.base

import android.view.View
import android.widget.EditText
import com.nytimesapp.data.utils.ErrorEntity
import okhttp3.ResponseBody

interface BaseListener {
    fun startLoading(viewLoader: View)
    fun stopLoading(viewLoader: View)
    fun onLoadDataFailure(errorEntity: ErrorEntity)
    fun onBusinessError(responseBody: ResponseBody?)
    fun onServerError()
    fun showSnackBar(msg: String)
    fun showShortToast(msg: String)
    fun showLongToast(msg: String)
    fun showAlertDialog(msg: String)
    fun onErrorField(editText: EditText?, msg: String)
}