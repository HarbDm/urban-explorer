package com.harbdm.urbanexplorer.presentation.ui.screens.about

import android.view.View
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.viewinterop.AndroidView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentContainerView

@Composable
fun AboutScreenHost() {
    val context = LocalContext.current
    val fragment = remember { AboutScreenFragment() }

    AndroidView(
        factory = { ctx ->
            FragmentContainerView(ctx).apply {
                id = View.generateViewId()
                (ctx as FragmentActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(id, fragment)
                    .commit()
            }
        },
        update = {}
    )
}