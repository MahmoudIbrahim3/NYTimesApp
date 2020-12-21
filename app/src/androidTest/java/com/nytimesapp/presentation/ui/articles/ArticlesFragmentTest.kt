package com.nytimesapp.presentation.ui.articles

import android.view.View
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.swipeDown
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimesapp.R
import com.nytimesapp.presentation.MainActivity
import com.nytimesapp.utils.RecyclerViewMatcher
import org.hamcrest.Matcher
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ArticlesFragmentTest {

    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isArticlesDataListVisible() {

        onView(withId(R.id.rvArticles)).check(matches(isDisplayed()))

        onView(withRecyclerView(R.id.rvArticles).atPositionOnView(1, R.id.tvTitle))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_isArticlesListRefreshed() {

        onView(withId(R.id.swipeToRefresh))
            .perform(withCustomConstraints(swipeDown(), isDisplayingAtLeast(85)))

        onView(withId(R.id.swipeToRefresh))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_clickItemFromTheList() {

        onView(withRecyclerView(R.id.rvArticles).atPositionOnView(1, R.id.tvTitle))
            .perform(click())
    }

    private fun withRecyclerView(recyclerViewId: Int): RecyclerViewMatcher {
        return RecyclerViewMatcher(recyclerViewId)
    }

    private fun withCustomConstraints(action: ViewAction, constraints: Matcher<View>): ViewAction? {
        return object : ViewAction {
            override fun getConstraints(): Matcher<View> {
                return constraints
            }

            override fun getDescription(): String {
                return action.description
            }

            override fun perform(uiController: UiController?, view: View?) {
                action.perform(uiController, view)
            }
        }
    }
}