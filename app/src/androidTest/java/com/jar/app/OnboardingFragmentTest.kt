package com.jar.app

import androidx.test.espresso.Espresso.*
import androidx.test.espresso.action.ViewActions
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.withId
import com.jar.app.base.di.module.AppModule
import com.jar.app.core_tests.base.BaseInstrumentTest
import com.jar.app.feature.onboarding.ui.enter_otp.EnterOtpFragment
import com.jar.app.feature.onboarding.ui.enter_otp.EnterOtpFragmentArgs
import com.jar.app.feature.onboarding.ui.onboarding_story.OnBoardingStoryFragment
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Test


@UninstallModules(AppModule::class)
@HiltAndroidTest
class OnboardingFragmentTest: BaseInstrumentTest() {
    override fun setUp() {
    }

    override fun tearDown() {
    }

    @Test
    fun testLaunchFragment() {
        val bundle = EnterOtpFragmentArgs(
            phoneNumber = "9999911111",
            countryCode = "+91",
            hasExperianConsent = true
        ).toBundle()

        launchFragmentInHiltContainer<EnterOtpFragment>(bundle)

        Thread.sleep(5000)

        onView(withId(R.id.otpView))
            .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
        Thread.sleep(2000)

        onView(ViewMatchers.withId(R.id.otpView))
            // Perform actions on the EditText
            .perform(
                // Type text into the EditText
                ViewActions.typeText("123456"),

                // Close the soft keyboard
                ViewActions.closeSoftKeyboard()
            );
        Thread.sleep(2000)

        onView(withId(R.id.btnVerify)).perform(ViewActions.click())

    }
}