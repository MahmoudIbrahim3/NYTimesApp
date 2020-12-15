package com.nytimesapp.presentation.utils

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.view.View
import android.view.inputmethod.InputMethodManager

object AppUtils {

    fun hideSoftKeyboard(activity: Activity?, view: View) {
        if(activity == null)
            return

        val imm = activity.getSystemService(Context.INPUT_METHOD_SERVICE)
                as InputMethodManager?
        imm!!.hideSoftInputFromWindow(view.windowToken, 0)
    }

}