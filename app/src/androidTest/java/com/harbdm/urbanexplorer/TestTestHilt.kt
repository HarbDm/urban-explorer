package com.harbdm.urbanexplorer

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
class SmokeHiltTest {
    @get:Rule
    val hilt = HiltAndroidRule(this)

    @Before
    fun setup() { hilt.inject() }

    @Test
    fun launches() { assertTrue(true) }
}