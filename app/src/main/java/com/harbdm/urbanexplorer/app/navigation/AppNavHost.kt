package com.harbdm.urbanexplorer.app.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import com.harbdm.urbanexplorer.core.navigation.FeatureEntry
import com.harbdm.urbanexplorer.feature.about.AboutEntry
import com.harbdm.urbanexplorer.feature.home.HomeEntry
import com.harbdm.urbanexplorer.feature.spots.SpotsEntry

val screenDestinations: List<FeatureEntry> = listOf(
    SpotsEntry,
    AboutEntry,
    HomeEntry
)

@Composable
fun AppNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController,
        startDestination = HomeEntry.route,
        modifier = modifier
    ) {
        screenDestinations.forEach {
            with(it) {
                register(navController)
            }
        }
    }
}