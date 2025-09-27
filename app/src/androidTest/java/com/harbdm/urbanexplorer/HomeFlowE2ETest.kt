package com.harbdm.urbanexplorer

import androidx.compose.ui.test.junit4.createAndroidComposeRule
import androidx.compose.ui.test.onAllNodesWithText
import androidx.compose.ui.test.onNodeWithTag
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import com.google.common.truth.Truth.assertThat
import com.harbdm.testing.fakes.FakeSpotRepository
import com.harbdm.urbanexplorer.app.MainActivity
import com.harbdm.urbanexplorer.di.module.DataModule
import com.harbdm.urbanexplorer.di.module.RepositoryModule
import com.harbdm.urbanexplorer.domain.repository.SpotRepository

import dagger.hilt.android.testing.HiltAndroidRule
import dagger.hilt.android.testing.HiltAndroidTest
import dagger.hilt.android.testing.UninstallModules
import org.junit.Assert.assertTrue

import org.junit.Before
import org.junit.Rule
import org.junit.Test
import javax.inject.Inject

@HiltAndroidTest
@UninstallModules(DataModule::class, RepositoryModule::class)
class HomeFlowE2ETest {
    @Inject lateinit var repo: SpotRepository


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
        val spotTitle = "Amazing Spot"

        //check home page and click new spot
        compose.onNodeWithText("My Spots").assertExists()
        compose.onNodeWithTag("new_spot_button").performClick()

        //assure we're on new spot screen and same repository
        compose.onNodeWithText("New Spot").assertExists()
        assertThat(FakeSpotRepository.createdCount()).isEqualTo(1)

        //filling all necessary fields
        compose.onNodeWithTag("title_textfield").performTextInput(spotTitle)

        compose.onNodeWithTag("type_dropdown_menu").performClick()
        compose.onNodeWithText("Cafe").performClick()

        compose.onNodeWithTag("description_textfield").performTextInput("test test")
        compose.onNodeWithTag("location_hint_textfield").performTextInput("test test")


        compose.onNodeWithTag("save_spot_button").performClick()

        compose.onNodeWithText("My Spots").assertExists()

        //waiting till nodes with title will appear on screen
        compose.waitUntil(timeoutMillis = 5_000) {
            compose.onAllNodesWithText(spotTitle).fetchSemanticsNodes().isNotEmpty()
        }
        compose.onNodeWithText(spotTitle).assertExists()
    }
}