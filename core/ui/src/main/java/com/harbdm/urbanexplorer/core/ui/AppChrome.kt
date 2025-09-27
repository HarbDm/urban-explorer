package com.harbdm.urbanexplorer.core.ui

import androidx.compose.runtime.staticCompositionLocalOf

interface AppChromeController {
    fun setTopBar(state: TopAppBarState)
    fun resetTopBar()
    fun showSnackbar(message: String)
}

//this controler  will be provided to app screens so they can call snackBar messages and change appbar
val LocalAppChrome = staticCompositionLocalOf<AppChromeController> {
    error("No UrbanExplorerShellViewModel provided")
}