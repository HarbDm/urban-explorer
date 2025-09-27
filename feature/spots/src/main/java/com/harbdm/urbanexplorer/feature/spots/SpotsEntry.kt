package com.harbdm.urbanexplorer.feature.spots

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.harbdm.urbanexplorer.core.navigation.AppRoutes
import com.harbdm.urbanexplorer.core.navigation.FeatureEntry
import com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.add_edit_spot.AddEditSpotScreen
import com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spot_details.SpotDetailsScreen
import com.harbdm.urbanexplorer.feature.spots.presentation.ui.screens.spots.SpotsScreen


object SpotsEntry: FeatureEntry {
    override val route = AppRoutes.SpotsScreen.route
    override fun NavGraphBuilder.register(navController: NavHostController) {

        composable(route = SpotsEntry.route){
            SpotsScreen(
                onNewSpotClicked = {
                    navController.navigate(AppRoutes.AddEditScreen.createRouteForNewSpot())
                },
                onAboutClicked = {
                    navController.navigate(AppRoutes.AboutScreen.route)
                },
                onExistingSpotClicked = { spotId ->
                    navController.navigate(
                        AppRoutes.SpotDetailsScreen.createRoute(spotId)
                    )
                }
            )
        }

        composable(
            route = AppRoutes.SpotDetailsScreen.route,
            arguments = AppRoutes.SpotDetailsScreen.navArguments
        ){ backStackEntry ->
            SpotDetailsScreen(
                onEditSpotClicked ={ spotId ->
                    navController.navigate(AppRoutes.AddEditScreen.createRouteForEditSpot(spotId))
                },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(
            route = AppRoutes.AddEditScreen.route,
            arguments = AppRoutes.AddEditScreen.navArguments
        ) { backStackEntry ->
            AddEditSpotScreen(
                onNavigateBack = { navController.popBackStack() }
            )
        }
    }

}