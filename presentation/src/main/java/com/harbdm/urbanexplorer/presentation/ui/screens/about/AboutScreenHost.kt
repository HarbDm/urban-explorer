package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.harbdm.urbanexplorer.presentation.shell.LocalShellViewModel
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState
import com.harbdm.urbanexplorer.presentation.ui.screens.spot_details.SpotDetailsEvent

@Composable
fun AboutScreenHost() {
    val context = LocalContext.current
    val fragment = remember { AboutScreenFragment() }

    // Controller of App Bar provided to change it on screen init
    val topAppBarController = LocalTopAppBarController.current

    // Shell VM, needed to show snackbar messages
    val urbanExplorerShellViewModel = LocalShellViewModel.current

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = "About",
                isBackButtonVisible = true
            )
        )
    }

    AndroidView(
        factory = { ctx ->
            FragmentContainerView(ctx).apply {
                id = View.generateViewId()
                val fragment = AboutScreenFragment().apply {
                    sendSnackbarMessage = { msg ->
                        urbanExplorerShellViewModel.showSnackbar(msg)
                    }
                }
                (ctx as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(id, fragment)
                    .commit()
            }
        },
        update = {},
        modifier = Modifier.fillMaxSize()
            .padding(horizontal = 20.dp)
    )
}