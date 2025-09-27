package com.harbdm.urbanexplorer.feature.about

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.harbdm.urbanexplorer.core.navigation.AppRoutes
import com.harbdm.urbanexplorer.core.navigation.FeatureEntry
import com.harbdm.urbanexplorer.feature.about.presentation.ui.screens.about.AboutScreenHost

object AboutEntry: FeatureEntry {
    override val route: String = AppRoutes.AboutScreen.route

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable (
            route = AppRoutes.AboutScreen.route
        ){  backStackEntry ->
            AboutScreenHost()
        }
    }
}