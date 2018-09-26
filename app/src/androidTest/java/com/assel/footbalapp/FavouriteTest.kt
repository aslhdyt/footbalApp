package com.assel.footbalapp


import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.IdlingRegistry
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers.*
import android.support.test.filters.MediumTest
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.view.View
import android.view.ViewGroup
import com.assel.footbalapp.activity.main.MainActivity
import com.assel.footbalapp.activity.main.schedule.ScheduleRecyclerAdapter
import com.assel.footbalapp.database.database
import org.hamcrest.Description
import org.hamcrest.Matcher
import org.hamcrest.Matchers.`is`
import org.hamcrest.Matchers.allOf
import org.hamcrest.TypeSafeMatcher
import org.hamcrest.core.IsInstanceOf
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@MediumTest
@RunWith(AndroidJUnit4::class)
class FavouriteTest {

    @get:Rule
    val mActivityTestRule: ActivityTestRule<MainActivity> = ActivityTestRule(MainActivity::class.java)

    @Before
    fun registerIdlingResource() {
        //start with empty database
        mActivityTestRule.activity.database.use {
            delete(DatabaseConst.TABLE_FAVOURITE)
        }
        val mainIdling = (mActivityTestRule.activity.application as App).idlingResource
        IdlingRegistry.getInstance().register(mainIdling)
    }


    @Test
    fun favouriteTest() {
        onView(
                allOf(withText("Last Event"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        0),
                                0),
                        isDisplayed()))

        onView(
                allOf(withText("Next Event"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        1),
                                0),
                        isDisplayed()))

        onView(
                allOf(withText("Favourite"),
                        childAtPosition(
                                childAtPosition(
                                        IsInstanceOf.instanceOf(android.widget.LinearLayout::class.java),
                                        2),
                                0),
                        isDisplayed()))

        onView(allOf(withId(R.id.recyclerView), isDisplayed()))
                .perform(RecyclerViewActions.actionOnItemAtPosition<ScheduleRecyclerAdapter.ViewHolder>(0, click()))

        onView(
                allOf(withId(R.id.ivAway),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                2),
                        isDisplayed()))

        onView(
                allOf(withId(R.id.ivHome),
                        childAtPosition(
                                childAtPosition(
                                        withId(android.R.id.content),
                                        0),
                                1),
                        isDisplayed()))

        onView(allOf(withId(R.id.favourite), withContentDescription("Favourite"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.action_bar),
                                        1),
                                0),
                        isDisplayed())).perform(click())
        pressBack()

        onView(
                allOf(childAtPosition(
                        childAtPosition(
                                withClassName(`is`("org.jetbrains.anko.design._TabLayout")),
                                0),
                        2),
                        isDisplayed())).perform(click())


        val textView = onView(
                allOf(withId(R.id.tvTitle), withText("Watford vs Man United"),
                        childAtPosition(
                                childAtPosition(
                                        withId(R.id.recyclerView),
                                        0),
                                0),
                        isDisplayed()))
        textView.check(matches(isDisplayed()))

    }


    private fun childAtPosition(
            parentMatcher: Matcher<View>, position: Int): Matcher<View> {

        return object : TypeSafeMatcher<View>() {
            override fun describeTo(description: Description) {
                description.appendText("Child at position $position in parent ")
                parentMatcher.describeTo(description)
            }

            public override fun matchesSafely(view: View): Boolean {
                val parent = view.parent
                return (parent is ViewGroup && parentMatcher.matches(parent)
                        && view == parent.getChildAt(position))
            }
        }
    }
}
