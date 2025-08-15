package com.harbdm.urbanexplorer.presentation.shell.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.height
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopAppBar(
    tittle: String,
    isBackButtonVisible: Boolean,
    actions: List<TopAppBarAction>,
    onNavigateBack: () -> Unit
) {
    CenterAlignedTopAppBar(
        title = {
            Text(
                text = tittle,
                style = MaterialTheme.typography.headlineMedium
            )
        },
        navigationIcon = {
            if (isBackButtonVisible){
                IconButton(onClick = onNavigateBack) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back"
                    )
                }
            }
        },
        actions = {
            actions.forEach { action->
                when(action){
                    is TopAppBarAction.ButtonAction -> {

                    }
                    is TopAppBarAction.IconAction -> {
                        IconButton(onClick = action.onClick) {
                            Icon(
                                imageVector = action.icon,
                                contentDescription = action.contentDescription
                            )
                        }
                    }
                }

            }
        }
    )
}