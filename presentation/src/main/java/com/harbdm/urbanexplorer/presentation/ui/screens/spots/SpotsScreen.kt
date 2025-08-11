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
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
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
    onExistingSpotClicked: (Long) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: SpotsViewModel = hiltViewModel()
) {
    val topAppBarController = LocalTopAppBarController.current

    LaunchedEffect(Unit) {
        topAppBarController.update(
            TopAppBarState(
                title = "My Spots",
                actions = listOf(
                    TopAppBarAction.IconAction(
                        icon = Icons.Default.Add,
                        contentDescription = "Add Spot",
                        onClick = onNewSpotClicked
                    )
                ),
                isBackButtonVisible = false
            )
        )

    }

    val mockList = listOf(
        Spot(
            spotName = "Test Park 2",
            spotType = "Cinema",
            spotDescription = "TODO()",
            locationHint = "Park",
            spotRating = 3,
            id = 1,
            timeStamp = 1000,
            photos = List(1) {
                Photo(
                    photoId = 1,
                    spotOwnerId = 1,
                    uriString = "android.resource://${LocalContext.current.packageName}/${R.drawable.park_testing}",
                    caption = "test"
                )
            }
        ),
        Spot(
            spotName = "Test Park",
            spotType = "Park",
            spotDescription = "TODO()",
            locationHint = "Park",
            spotRating = 3,
            id = 2,
            timeStamp = 1000,
            photos = List(1) {
                Photo(
                    photoId = 1,
                    spotOwnerId = 2,
                    uriString = "android.resource://${LocalContext.current.packageName}/${R.drawable.park_testing}",
                    caption = "test"
                )
            }
        )
    )

    val multiMockList = mockList +mockList +mockList +mockList +mockList +mockList +mockList +mockList +
            mockList +mockList +mockList +mockList +mockList +mockList
    Column(modifier = modifier) {
        LazyColumn(modifier = Modifier.fillMaxSize()) {
            items(multiMockList){ spot ->
                SpotCard(
                    spot = spot,
                    onClick = onExistingSpotClicked
                )
                Spacer(modifier = Modifier.height(16.dp))
            }
        }
    }
}