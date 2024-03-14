package com.jar.app.feature.onboarding

import androidx.lifecycle.Lifecycle
import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withParent
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.jar.app.base.di.module.AppModule
import com.jar.app.core_tests.base.BaseInstrumentTest
import com.jar.app.feature.home.ui.activity.HomeActivity
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.hamcrest.CoreMatchers.allOf
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@UninstallModules(AppModule::class)
@RunWith(AndroidJUnit4::class)
@HiltAndroidTest
class HomeActivityTest : BaseInstrumentTest() {

    private lateinit var scenario: ActivityScenario<HomeActivity>

    @Before
    override fun setUp() {
        super.setUp()
        scenario = ActivityScenario.launch(HomeActivity::class.java)
        scenario.moveToState(Lifecycle.State.RESUMED)
    }

    @Test
    fun test(){
        // check splash screen is visible or not.
        onView(withId(com.jar.app.R.id.drawerLayout)).check(matches(isDisplayed()))
        onView(allOf(withId(com.jar.app.R.id.nav_host_fragment), withParent(withId(com.jar.app.R.id.container_layout)))).check(matches(isDisplayed()))
    }
}