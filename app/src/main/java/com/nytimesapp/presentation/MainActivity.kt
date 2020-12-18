package com.nytimesapp.presentation

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.nytimesapp.R
import dagger.android.AndroidInjection
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import kotlinx.android.synthetic.main.activity_main.*
import javax.inject.Inject

class MainActivity : AppCompatActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingSupportFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =
        dispatchingSupportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AndroidInjection.inject(this)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
    }

    fun showActionBar() {
        appBar.visibility = View.VISIBLE
    }

    fun hideActionBar() {
        appBar.visibility = View.GONE
    }

    fun setActionBarTitle(title: String?, isDisplayHome: Boolean) {
        supportActionBar?.setDisplayHomeAsUpEnabled(isDisplayHome)
        supportActionBar?.title = title
        if (isDisplayHome)
            toolbar.setNavigationOnClickListener { onBackPressed() }
    }
}