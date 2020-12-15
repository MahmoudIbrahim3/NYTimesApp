package com.nytimesapp.data.local

import android.content.Context
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DataManager @Inject constructor(private val context: Context,
                                      private val sharedPrefsHelper: SharedPrefsHelper
) {

}