package com.harbdm.urbanexplorer

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.harbdm.urbanexplorer.presentation.shell.UrbanExplorerShell
import com.harbdm.designsystem.UrbanExplorerTheme
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

