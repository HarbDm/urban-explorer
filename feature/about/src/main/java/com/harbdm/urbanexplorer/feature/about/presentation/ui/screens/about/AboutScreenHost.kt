package com.harbdm.urbanexplorer.feature.about.presentation.ui.screens.about

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView
import com.harbdm.urbanexplorer.core.ui.LocalAppChrome
import com.harbdm.urbanexplorer.core.ui.TopAppBarState

/**
 * This screen is just a wrapper to host view-based screen. Decided to go with wrapper
 * to not mix compose and view based navigation. In case implementing mixed navigation
 * in future consider removing wrapper.
 */
@Composable
fun AboutScreenHost() {


    // app chrome, needed to show snackbar messages and change TopAppBar
    val appChrome = LocalAppChrome.current

    LaunchedEffect(Unit) {
        appChrome.setTopBar(
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
                        appChrome.showSnackbar(msg)
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