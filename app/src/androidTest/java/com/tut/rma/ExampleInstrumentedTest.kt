package com.tut.rma

import android.content.Intent
import androidx.test.core.app.launchActivity
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.*
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.ext.junit.runners.AndroidJUnit4

import org.junit.Test
import org.junit.runner.RunWith

import org.junit.Assert.*

/**
 * Instrumented test, which will execute on an Android device.
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
@RunWith(AndroidJUnit4::class)
class ExampleInstrumentedTest {
    @Test
    fun useAppContext() {
        // Context of the app under test.
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        assertEquals("com.tut.rma", appContext.packageName)
    }
    @Test
    fun testDetailActivityInstantiation(){
        val pokreniDetalje: Intent = Intent(MainActivity2::javaClass.name)
        pokreniDetalje.putExtra("movie_title","Pulp Fiction")
        val scenario = launchActivity<MainActivity2>(pokreniDetalje)
        //  onView(withId(R.id.movie_title)).check(matches(withText("Pulp Fiction")))
        //onView(withId(R.id.movie_genre)).check(matches(withText("crime")))
        //onView(withId(R.id.movie_overview)).check(matches(withSubstring("pair of diner bandits")))
    }
}