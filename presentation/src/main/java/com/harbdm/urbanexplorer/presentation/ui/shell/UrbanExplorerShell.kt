package com.harbdm.urbanexplorer.presentation.ui.shell

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.harbdm.urbanexplorer.presentation.ui.shell.components.MainTopAppBar
import com.harbdm.urbanexplorer.presentation.ui.screens.spots.SpotsScreen

@Composable
fun UrbanExplorerShell(
    modifier: Modifier = Modifier
) {
    Scaffold(
        topBar = {
            MainTopAppBar(
                mainText = "My Spots"
            )
                 },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        SpotsScreen(modifier = Modifier.padding(innerPadding))
    }
}