package com.fd.kso.ui.main

import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.navigation.Navigation
import androidx.navigation.testing.TestNavHostController
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.Espresso.pressBack
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.fd.kso.R
import com.fd.kso.ui.MainActivity
import com.fd.kso.ui.adapters.MItemAdapter
import com.fd.kso.utils.EspressoIdlingResourceRule
import com.fd.kso.utils.UITestUtil
import org.junit.FixMethodOrder
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.MethodSorters

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4::class)
class MainFragmentTest {

    private val LIST_ITEM_IN_TEST = 0

    @get:Rule
    val activityRule = ActivityScenarioRule(MainActivity::class.java)

    @get: Rule
    val espressoIdlingResoureRule = EspressoIdlingResourceRule()

    @Test
    fun test_navigation_to_main_screen() {

        val navController = TestNavHostController(
            ApplicationProvider.getApplicationContext()
        )

        val mainSenario = launchFragmentInContainer<MainFragment>()

        mainSenario.onFragment { fragment ->
            navController.setGraph(R.navigation.nav_graph)
            Navigation.setViewNavController(fragment.requireView(), navController)
        }
    }

    @Test
    fun test_if_fragment_main_is_visible() {

        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
        onView(withId(R.id.list_progress_bar)).check(matches(isDisplayed()))
    }

    @Test
    fun test_item_selected_and_detail_is_visible() {

        onView(withId(R.id.recycler_view)).run {
            check(matches(isDisplayed()))
            perform(actionOnItemAtPosition<MItemAdapter.DataViewHolder>(LIST_ITEM_IN_TEST, click()))
        }
        onView(withId(R.id.myMap)).check(matches(isDisplayed()))
    }

    @Test
    fun test_backNavigation_to_main_fragment() {

        onView(withId(R.id.recycler_view))
            .perform(actionOnItemAtPosition<MItemAdapter.DataViewHolder>(LIST_ITEM_IN_TEST, click()))
        onView(withId(R.id.myMap)).check(matches(isDisplayed()))
        pressBack()
        onView(withId(R.id.recycler_view)).check(matches(isDisplayed()))
    }

}