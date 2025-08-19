package com.harbdm.urbanexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.harbdm.urbanexplorer.presentation.shell.UrbanExplorerShellViewModel
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.AddEditSpotScreen
import com.harbdm.urbanexplorer.presentation.ui.screens.spot_details.SpotDetailsScreen
import com.harbdm.urbanexplorer.presentation.ui.screens.spots.SpotsScreen

@Composable
fun PresentationNavHost(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController = navController,
        startDestination = "shell_graph",
        modifier = modifier
    ) {
        navigation(
            startDestination = ScreenRoute.SpotsScreen.route,
            route = "shell_graph"
        ) {
            composable(
                route = ScreenRoute.SpotsScreen.route
            ) {
                SpotsScreen(
                    onNewSpotClicked = {
                        navController.navigate(ScreenRoute.AddEditScreen.createRouteForNewSpot())
                    },
                    onExistingSpotClicked = { spotId ->
                        navController.navigate(
                            ScreenRoute.SpotDetailsScreen.createRoute(spotId)
                        )
                    }
                )
            }

            composable(
                route = ScreenRoute.SpotDetailsScreen.route,
                arguments = ScreenRoute.SpotDetailsScreen.navArguments
            ){ backStackEntry ->
                SpotDetailsScreen(
                    onEditSpotClicked ={ spotId ->
                        navController.navigate(ScreenRoute.AddEditScreen.createRouteForEditSpot(spotId))
                    },
                    onNavigateBack = { navController.popBackStack() }
                )
            }

            composable(
                route = ScreenRoute.AddEditScreen.route,
                arguments = ScreenRoute.AddEditScreen.navArguments
            ) { backStackEntry ->
                AddEditSpotScreen(
                    onNavigateBack = { navController.popBackStack() }
                )
            }
        }
    }
}