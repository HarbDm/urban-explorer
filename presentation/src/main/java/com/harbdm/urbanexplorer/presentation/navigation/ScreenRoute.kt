package com.harbdm.urbanexplorer.presentation.navigation

import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument

sealed class ScreenRoute(
    val route: String,
    val navArguments: List<NamedNavArgument> = emptyList()
) {
    object SpotsScreen : ScreenRoute("spots_list_screen")

    object SpotDetailsScreen: ScreenRoute(
            route = "spots_list_screen?spotId={spotId}",
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
        fun createRoute(spotId: Long) = "spots_list_screen?spotId=$spotId"
    }

    object AddEditScreen : ScreenRoute(
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

    object AboutScreen : ScreenRoute("about_screen")
}