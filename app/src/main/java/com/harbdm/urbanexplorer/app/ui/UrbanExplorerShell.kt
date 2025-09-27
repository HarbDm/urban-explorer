package com.harbdm.urbanexplorer.app.ui

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
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.rememberNavController
import com.harbdm.urbanexplorer.app.navigation.AppNavHost
import com.harbdm.urbanexplorer.app.ui.components.MainTopAppBar
import com.harbdm.urbanexplorer.core.ui.AppChromeController
import com.harbdm.urbanexplorer.core.ui.LocalAppChrome
import com.harbdm.urbanexplorer.core.ui.TopAppBarState


@Composable
fun UrbanExplorerShell(
    urbanExplorerShellViewModel: UrbanExplorerShellViewModel = hiltViewModel()
) {
    Log.d("ViewModelCheck", "Shell shellViewModel instance: $urbanExplorerShellViewModel")

    val navController = rememberNavController()


    val snackbarHostState = remember { SnackbarHostState() }
    val shellState = urbanExplorerShellViewModel.topAppBarState.collectAsState()

    LaunchedEffect(snackbarHostState) {
        urbanExplorerShellViewModel.snackbarMessages.collect { message ->
            snackbarHostState.showSnackbar(message = message)
        }
    }

    val chrome = remember {
        object : AppChromeController {
            override fun setTopBar(state: TopAppBarState) =
                urbanExplorerShellViewModel.updateAppBar(state)

            override fun resetTopBar() = urbanExplorerShellViewModel.resetAppBar()

            override fun showSnackbar(message: String) =
                urbanExplorerShellViewModel.showSnackbar(message)

        }
    }

    // Hovering all nested screens in provider to have access to TopAppBar and
    // snackbar messages in all needed screens
    CompositionLocalProvider(LocalAppChrome provides chrome) {
        Scaffold(
            snackbarHost = {
                SnackbarHost(hostState = snackbarHostState)
            },
            topBar = {
                MainTopAppBar(
                    tittle = shellState.value.title,
                    isBackButtonVisible = shellState.value.isBackButtonVisible,
                    actions = shellState.value.actions,
                    onNavigateBack = { navController.navigateUp() },
                )
            },
            modifier = Modifier.fillMaxSize()
        ) { innerPadding ->
            AppNavHost(
                navController = navController,
                modifier = Modifier.padding(innerPadding)
            )
        }

    }

}
