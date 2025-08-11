package com.harbdm.urbanexplorer

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.harbdm.urbanexplorer.presentation.shell.UrbanExplorerShell
import com.harbdm.urbanexplorer.ui.theme.UrbanExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UrbanExplorerTheme {
                UrbanExplorerShell()
            }
        }
    }
}

