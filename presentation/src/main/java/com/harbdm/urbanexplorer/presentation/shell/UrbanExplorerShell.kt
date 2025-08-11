package com.harbdm.urbanexplorer.presentation.shell

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.harbdm.urbanexplorer.presentation.navigation.PresentationNavGraph
import com.harbdm.urbanexplorer.presentation.shell.components.MainTopAppBar
import com.harbdm.urbanexplorer.presentation.ui.screens.spots.SpotsScreen

@Composable
fun UrbanExplorerShell(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val topAppBarController = remember { TopAppBarController() }

    val topAppBarState by topAppBarController.topAppBarState.collectAsState()

    CompositionLocalProvider(LocalTopAppBarController provides topAppBarController) {
        Scaffold(
            topBar = {
                MainTopAppBar(
                    tittle = topAppBarState.title,
                    isBackButtonVisible = topAppBarState.isBackButtonVisible,
                    actions = topAppBarState.actions,
                    onNavigateBack = { navController.navigateUp() },
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            PresentationNavGraph(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }


}