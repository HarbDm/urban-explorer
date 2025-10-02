package com.harbdm.urbanexplorer.app

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.harbdm.urbanexplorer.app.presentation.UrbanExplorerShell
import com.harbdm.urbanexplorer.core.designsystem.UrbanExplorerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
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

