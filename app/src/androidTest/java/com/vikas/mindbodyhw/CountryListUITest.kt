package com.vikas.mindbodyhw

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vikas.mindbodyhw.list.CountryRecyclerViewAdapter
import com.vikas.mindbodyhw.list.CountryListActivity
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class CountryListUITest {
    lateinit var mActivityRule: ActivityScenario<CountryListActivity>

    @Before
    fun setup() {
        mActivityRule = ActivityScenario.launch(CountryListActivity::class.java)
    }

    @Test
    fun testCountryList() {
        onView(withId(R.id.progressBar)).check(
            matches(isDisplayed())
        )
        onView(withId(R.id.countryRecyclerView)).check(
            matches(isDisplayed())
        )
    }

    @Test
    fun testCountryDetails() {
        Thread.sleep(2000)
        onView(withId(R.id.countryRecyclerView))
            .perform(actionOnItemAtPosition<CountryRecyclerViewAdapter.CountryViewHolder>(1, click()))
        onView(withId(R.id.provinceRecyclerView)).check(
            matches(isDisplayed())
        )
    }
}