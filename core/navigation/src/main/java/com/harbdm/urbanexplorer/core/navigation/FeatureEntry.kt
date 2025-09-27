package com.harbdm.urbanexplorer.core.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController

interface FeatureEntry {
    val route: String
    fun NavGraphBuilder.register(navController: NavHostController)
}