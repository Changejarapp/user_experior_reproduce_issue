package com.jar.app

import com.jar.app.base.util.getElapsedTimeInDays
import com.jar.app.base.util.getElapsedTimeInDaysV2
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }


    // Unit tests for BaseExtension.getElapsedTimeInDaysV2 function
    @Test
    fun `test elapsed time in days when difference is less than a day`() {
        val currentTime = System.currentTimeMillis()
        val timestamp = currentTime - (1000 * 60 * 60 * 12) // 12 hours ago
        val newElapsedTime = timestamp.getElapsedTimeInDaysV2()
        assertEquals(0, newElapsedTime)
    }

    @Test
    fun `test elapsed time in days when difference is exactly one day`() {
        val currentTime = System.currentTimeMillis()
        val timestamp = currentTime - (1000 * 60 * 60 * 24) // 1 day ago
        val elapsedTime = timestamp.getElapsedTimeInDaysV2()
        assertEquals(1, elapsedTime)
    }

    @Test
    fun `test elapsed time in days when difference is more than one day`() {
        val currentTime = System.currentTimeMillis()
        val timestamp = currentTime - (1000 * 60 * 60 * 24 * 5) // 5 days ago
        val elapsedTime = timestamp.getElapsedTimeInDaysV2()
        assertEquals(5, elapsedTime)
    }

    @Test
    fun `test elapsed time in days for very large difference`() {
        val currentTime = System.currentTimeMillis()
        val timestamp = currentTime - (1000L * 60 * 60 * 24 * 100000) // 100000 days ago
        val elapsedTime = timestamp.getElapsedTimeInDaysV2()
        assertEquals(100000, elapsedTime)
    }
    @Test
    fun `test elapsed time in days for specific timestamp`() {
        val timestamp = 1614307200000L // March 1, 2021 (UTC)
        val elapsedTime = timestamp.getElapsedTimeInDaysV2()
        assertEquals(1096, elapsedTime) // 723 days have passed since March 1, 2021
    }
}