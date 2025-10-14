package com.harbdm.urbanexplorer.feature.home.presentation.ui.screens.home

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.text.style.TextAlign
import com.harbdm.urbanexplorer.core.ui.LocalAppChrome
import com.harbdm.urbanexplorer.core.ui.TopAppBarAction
import com.harbdm.urbanexplorer.core.ui.TopAppBarState

@Composable
fun HomeScreen(
    modifier: Modifier = Modifier
) {

    // app chrome, needed to show snackbar messages and change TopAppBar
    val appChrome = LocalAppChrome.current
    LaunchedEffect(Unit) {
        appChrome.setTopBar(
            TopAppBarState(
                title = "Urban Explorer",
                isBackButtonVisible = false
            )
        )

    }
        Text(
            text ="Under Construction",
            modifier = Modifier.fillMaxSize(),
            textAlign = TextAlign.Center

        )
}