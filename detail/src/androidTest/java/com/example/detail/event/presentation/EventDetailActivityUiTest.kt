package com.example.detail.event.presentation

import android.content.Intent
import android.view.View
import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.core.app.ApplicationProvider
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.UiController
import androidx.test.espresso.ViewAction
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.LargeTest
import org.hamcrest.Matcher

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@LargeTest
@RunWith(AndroidJUnit4::class)
class EventDetailActivityUiTest {

    private lateinit var scenario: ActivityScenario<EventDetailActivity>
    @Before
    fun setUp() {
        val eventString = """{"business":{"id":"Ik4uE8RQJihiogbsM66kDQ","imageRestaurant":["https://s3-media4.fl.yelpcdn.com/bphoto/uG3bUhn6ADzHmMlZQ6-QOw/o.jpg"],"isFavorite":false,"name":"Borough of Manhattan"},"cost":0,"description":"What the heck is this? Back by popular demand, this is an essential list of at least one Yelp-approved activity to accomplish every week throughout 2016....","id":"new-york-yelps-51-things-to-do-in-2016","imageUrl":"https://s3-media3.fl.yelpcdn.com/ephoto/5lHM0s4B6E7ISu-Eq7kaNA/o.jpg","isFavorite":false,"isFree":true,"location":{"city":"New York"},"name":"Yelp\u0027s 51 Things To Do In 2016","timeEnd":"2017-01-01 07:30","timeStart":"2016-12-05 08:30"}"""
        val intent = Intent(ApplicationProvider.getApplicationContext(), EventDetailActivity::class.java).apply {
            putExtra(EventDetailActivity.ARG_NAME, eventString)
        }
        scenario = ActivityScenario.launch(intent)
        scenario.moveToState(Lifecycle.State.CREATED)
    }

    @Test
    fun should_work_ui() {
        scenario.moveToState(Lifecycle.State.RESUMED)
        onView(ViewMatchers.isRoot()).perform(waitFor(6000))
    }

    private fun waitFor(delay: Long): ViewAction {
        return object: ViewAction {
            override fun getConstraints(): Matcher<View> {
                return ViewMatchers.isRoot()
            }

            override fun getDescription(): String = "i'm being delayed by $delay milliseconds"

            override fun perform(uiController: UiController, view: View?) {
                uiController.loopMainThreadForAtLeast(delay)
            }

        }
    }
}