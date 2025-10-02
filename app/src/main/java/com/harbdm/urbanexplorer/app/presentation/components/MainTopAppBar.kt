package com.harbdm.urbanexplorer.app.presentation.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.harbdm.urbanexplorer.core.ui.TopAppBarAction

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
                        IconButton(
                            onClick = action.onClick,
                            modifier = action.modifier
                        ) {
                            Icon(
                                imageVector = action.icon,
                                contentDescription = action.contentDescription,

                            )
                        }
                    }
                }

            }
        }
    )
}