package com.nytimesapp.presentation.ui.articledetails

import android.content.Intent
import android.os.Bundle
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.nytimesapp.presentation.ui.articles.ArticlesActivity
import com.nytimesapp.R
import com.nytimesapp.presentation.ui.base.BaseActivity
import com.nytimesapp.presentation.utils.AppConst
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

/**
 * An activity representing a single Item detail screen. This
 * activity is only used on narrow width devices. On tablet-size devices,
 * item details are presented side-by-side with a list of items
 * in a [ArticlesActivity].
 */
class ArticleDetailActivity : BaseActivity(), HasSupportFragmentInjector {

    @Inject
    lateinit var dispatchingSupportFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector() =
        dispatchingSupportFragmentInjector

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_article_detail)
        setSupportActionBar(findViewById(R.id.detail_toolbar))

        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
            Snackbar.make(view, "Replace with your own detail action", Snackbar.LENGTH_LONG)
                .setAction("Action", null).show()
        }

        // Show the Up button in the action bar.
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        if (savedInstanceState == null) {
            // Create the detail fragment and add it to the activity
            // using a fragment transaction.
            val fragment = ArticleDetailFragment()
                .apply {
                    arguments = Bundle().apply {
                        putString(
                            AppConst.INTENT_ITEM_ENTITY,
                            intent.getStringExtra(AppConst.INTENT_ITEM_ENTITY)
                        )
                    }
                }

            supportFragmentManager.beginTransaction()
                .add(R.id.item_detail_container, fragment)
                .commit()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem) =
            when (item.itemId) {
                android.R.id.home -> {

                    // This ID represents the Home or Up button. In the case of this
                    // activity, the Up button is shown. For
                    // more details, see the Navigation pattern on Android Design:
                    //
                    // http://developer.android.com/design/patterns/navigation.html#up-vs-back

                    navigateUpTo(Intent(this, ArticlesActivity::class.java))

                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}