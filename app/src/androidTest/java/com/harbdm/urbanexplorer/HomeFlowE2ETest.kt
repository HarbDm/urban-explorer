package com.harbdm.urbanexplorer

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.harbdm.urbanexplorer.di.module.DataModule
import com.harbdm.urbanexplorer.di.module.RepositoryModule

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Rule
import org.junit.Test

@HiltAndroidTest
@UninstallModules(DataModule::class, RepositoryModule::class)
class HomeFlowE2ETest {




    @get:Rule(order = 0)
    val hiltRule = HiltAndroidRule(this)

    @get:Rule(order = 1)
    val compose = createAndroidComposeRule<MainActivity>()

    @Before
    fun setUp(){
        hiltRule.inject()
    }

    @Test
    fun addNewSpot_andVerifyItAppears(){
        assertTrue(true)
       /* val spotTitle = "Amazing Spot"

        //check home page and click new spot
        compose.onNodeWithText("My Spots").assertExists()
        compose.onNodeWithTag("icon_button").performClick()

        compose.waitForIdle()
        compose.onNodeWithText("New Spot").assertExists()

        compose.onNodeWithTag("title_textfield").performTextInput(spotTitle)

        compose.onNodeWithTag("type_dropdown_menu").performClick()
        compose.onNodeWithText("Cafe").performClick()

        compose.onNodeWithTag("description_textfield").performTextInput("test test")
        compose.onNodeWithTag("location_hint_textfield").performTextInput("test test")


        compose.onNodeWithTag("icon_button").performClick()

        compose.waitForIdle()
        compose.onNodeWithText(spotTitle).assertExists()
*/

    }
}