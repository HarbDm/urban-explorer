package com.harbdm.urbanexplorer.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.harbdm.urbanexplorer.presentation.ui.screens.add_edit_spot.AddEditSpotScreen
import com.harbdm.urbanexplorer.presentation.ui.screens.spots.SpotsScreen

@Composable
fun PresentationNavGraph(
    navController: NavHostController,
    modifier: Modifier = Modifier
) {
    NavHost(
        navController=navController,
        startDestination = ScreenRoute.SpotsScreen.route,
        modifier = modifier
    ){
        composable(
            route = ScreenRoute.SpotsScreen.route
        ){
            SpotsScreen(
                onNewSpotClicked = {
                    navController.navigate(ScreenRoute.AddEditScreen.createRouteForNewSpot())
                },
                onExistingSpotClicked = { spotId ->
                    navController.navigate(ScreenRoute.AddEditScreen.createRouteForEditSpot(spotId))
                }
            )
        }

        composable(
            route = ScreenRoute.AddEditScreen.route,
            arguments = ScreenRoute.AddEditScreen.navArguments
        ) { backStackEntry->
            AddEditSpotScreen(
                onNavigateBack = {navController.popBackStack()}
            )
        }
    }
}