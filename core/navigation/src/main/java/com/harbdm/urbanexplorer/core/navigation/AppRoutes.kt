package com.harbdm.urbanexplorer.core.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class AppRoutes(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object SpotsScreen : AppRoutes("my_spots_screen")

    object SpotDetailsScreen: AppRoutes(
        route = "spot_details_screen?spotId={spotId}",
        navArguments = listOf(
            navArgument(
                name = "spotId"
            ) {
                type = NavType.LongType
                //no need for default value for now, cause screen should never
                //been called without argument
            }
        )
    ) {
        fun createRoute(spotId: Long) = "spot_details_screen?spotId=$spotId"
    }

    object AddEditScreen : AppRoutes(
        route = "add_edit_screen?spotId={spotId}",
        navArguments = listOf(
            navArgument(
                name = "spotId"
            ) {
                type = NavType.LongType
                defaultValue = -1L
            }
        )
    ) {
        fun createRouteForEditSpot(spotId: Long) = "add_edit_screen?spotId=$spotId"

        fun createRouteForNewSpot() = "add_edit_screen"
    }

    object AboutScreen : AppRoutes("about_screen")
}
