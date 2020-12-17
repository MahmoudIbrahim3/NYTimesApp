package com.nytimesapp.presentation.ui

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimesapp.R
import com.nytimesapp.presentation.MainActivity
import com.nytimesapp.utils.RecyclerViewMatcher
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticlesFragmentTest {

    @Before
    fun init() {
        ActivityScenario.launch(MainActivity::class.java)
    }

    @Test
    fun appLaunchesSuccessfully() {
        onView(withId(R.id.rvArticles)).check(matches(isDisplayed()))
        Thread.sleep(3000)
    }

    @Test
    fun recycleViewRenderedArticlesProperly() {
        onView(withRecyclerView(R.id.rvArticles)
            .atPositionOnView(1, R.id.tvTitle))
            .check(matches(isDisplayed()))

        Thread.sleep(1000)
    }

    @Test
    fun clickItem() {
        Thread.sleep(1000)

        onView(withRecyclerView(R.id.rvArticles)
            .atPositionOnView(1, R.id.tvTitle)).perform(click())

        Thread.sleep(1000)
    }

    fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }
}