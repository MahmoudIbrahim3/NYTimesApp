package com.nytimesapp.presentation.ui.articledetails

import android.os.Bundle
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.google.gson.Gson
import com.nytimes.entities.articles.ArticleEntity
import com.nytimesapp.R
import com.nytimesapp.presentation.utils.AppConst
import org.junit.Test
import org.junit.runner.RunWith
import androidx.fragment.app.testing.launchFragmentInContainer as launchFragmentInContainer

@RunWith(AndroidJUnit4::class)
class ArticleDetailsFragmentTest {

    @Test
    fun test_isArticleDataVisible() {
        val articleEntity = ArticleEntity(
            id = "1",
            title = "article title",
            abstract = "article abstract"
        )

        val args = Bundle()
        args.putString(AppConst.INTENT_ITEM_ENTITY, Gson().toJson(articleEntity))
        val scenario = launchFragmentInContainer<ArticleDetailsFragment>(
            fragmentArgs = args
        )

        onView(withId(R.id.tvTitle))
            .check(matches(withText(articleEntity.title)))

        onView((withId(R.id.tvDescription)))
            .check(matches(withText(articleEntity.abstract)))
    }
}