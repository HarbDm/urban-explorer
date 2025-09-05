package com.harbdm.urbanexplorer.presentation.ui.screens.spots

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.testTag
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.harbdm.urbanexplorer.domain.model.Photo
import com.harbdm.urbanexplorer.domain.model.Spot
import com.harbdm.urbanexplorer.presentation.ui.screens.spots.components.SpotCard
import com.harbdm.urbanexplorer.presentation.R
import com.harbdm.urbanexplorer.presentation.shell.LocalTopAppBarController
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarAction
import com.harbdm.urbanexplorer.presentation.shell.TopAppBarState

@Composable
fun SpotsScreen(
    onNewSpotClicked: () -> Unit,
    onAboutClicked: () -> Unit,
    onExistingSpotClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SpotsViewModel = hiltViewModel()
) {
    val uiState by viewModel.state.collectAsState()

    // Controller of App Bar provided to change it on screen init
    val topAppBarController = LocalTopAppBarController.current

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = "My Spots",
                actions = listOf(
                    TopAppBarAction.IconAction(
                        icon = Icons.Default.Info,
                        contentDescription = "About",
                        onClick = onAboutClicked
                    ),
                    TopAppBarAction.IconAction(
                        icon = Icons.Default.Add,
                        contentDescription = "Add Spot",
                        onClick = onNewSpotClicked,
                        modifier = Modifier.testTag("new_spot_button")
                    )
                ),
                isBackButtonVisible = false
            )
        )

    }

    Column(modifier = modifier.padding(horizontal = 20.dp)) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(uiState.spots){ spot ->
                SpotCard(
                    spot = spot,
                    onClick = onExistingSpotClicked
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}