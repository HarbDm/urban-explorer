package com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState

@Composable
fun AddEditSpotScreen(
    onNavigateBack: () -> Unit,
    modifier: Modifier = Modifier,
    viewModel: AddEditSpotViewModel = hiltViewModel()
) {
    val uiState by viewModel.spotState.collectAsState()

    val topAppBarController = LocalTopAppBarController.current

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = if(viewModel.spotState.value.spotId.toInt() !=-1) "Edit Spot" else "New Spot",
                isBackButtonVisible = true
            )
        )

    }

    Text(text = uiState.spotId.toString())
}