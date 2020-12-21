package com.nytimesapp.presentation

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.nytimesapp.R
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get: Rule
    val activityScenario = ActivityScenarioRule(MainActivity::class.java)

    @Test
    fun test_isActivityLaunched() {
        onView(withId(R.id.main_layout))
            .check(matches(isDisplayed()))
    }

    @Test
    fun test_visibilityTitle() {
        onView(withId(R.id.appBar))
            .check(matches(isDisplayed()))
    }
}