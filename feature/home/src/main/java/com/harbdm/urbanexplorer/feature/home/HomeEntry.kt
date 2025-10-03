package com.harbdm.urbanexplorer.feature.home

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.harbdm.urbanexplorer.core.navigation.AppRoutes
import com.harbdm.urbanexplorer.core.navigation.FeatureEntry
import com.harbdm.urbanexplorer.feature.home.presentation.ui.screens.home.HomeScreen

object HomeEntry : FeatureEntry {
    override val route = AppRoutes.HomeScreen.route

    override fun NavGraphBuilder.register(navController: NavHostController) {
        composable(route = HomeEntry.route){
            HomeScreen()
        }
    }
}