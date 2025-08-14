package com.harbdm.urbanexplorer.presentation.shell

import android.util.Log
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.harbdm.urbanexplorer.presentation.navigation.PresentationNavHost
import com.harbdm.urbanexplorer.presentation.navigation.ScreenRoute
import com.harbdm.urbanexplorer.presentation.shell.components.MainTopAppBar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest

@Composable
fun UrbanExplorerShell(
    urbanExplorerShellViewModel: UrbanExplorerShellViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    Log.d("ViewModelCheck", "Shell ViewModel instance: $urbanExplorerShellViewModel")
    val topAppBarController = remember { TopAppBarController() }

    val snackbarHostState = remember { SnackbarHostState() }

    val topAppBarState by topAppBarController.topAppBarState.collectAsState()

    LaunchedEffect(snackbarHostState) {
        urbanExplorerShellViewModel.snackbarMessages.collect { message ->
            snackbarHostState.showSnackbar(message = message)
        }
    }
    CompositionLocalProvider(LocalShellViewModel provides urbanExplorerShellViewModel) {
        CompositionLocalProvider(LocalTopAppBarController provides topAppBarController) {
            Scaffold(
                snackbarHost = {
                    SnackbarHost(hostState = snackbarHostState)
                },
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
                PresentationNavHost(
                    navController = navController,
                    modifier = Modifier.padding(innerPadding)
                )
            }

        }

    }
}