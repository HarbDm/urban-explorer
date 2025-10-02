package com.harbdm.urbanexplorer.app.presentation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.harbdm.urbanexplorer.R
import com.harbdm.urbanexplorer.core.navigation.AppRoutes

@Composable
fun BottomNavigationBar(
    navController: NavController,
    modifier: Modifier = Modifier
) {
    val navigationItems = getNavigationItems()

    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    val selectedNavigationIndex = navigationItems.indexOfFirst {
        it.route == currentRoute
    }.takeIf { it>=0 } ?:0

    NavigationBar(modifier = modifier) {
        navigationItems.forEachIndexed { index, item ->
            NavigationBarItem(
                selected = selectedNavigationIndex == index,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(0)
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(item.icon),
                        contentDescription = stringResource(item.tittle)
                    )
                },
                label = {
                    Text(
                        text = stringResource(item.tittle)
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = MaterialTheme.colorScheme.surface,
                    indicatorColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    }
}

private fun getNavigationItems() =
    listOf(
       /* BottomBarItem(
            R.string.bottom_navigation_home,
            R.drawable.ic_home,
            AppRoutes.HomeScreen.route
        ),*/
        BottomBarItem(
            R.string.bottom_navigation_my_spots,
            R.drawable.ic_my_spots,
            AppRoutes.SpotsScreen.route
        ),
        BottomBarItem(
            R.string.bottom_navigation_about,
            R.drawable.ic_info,
            AppRoutes.AboutScreen.route
        )
    )

data class BottomBarItem(
    @StringRes val tittle: Int,
    @DrawableRes val icon: Int,
    val route: String
)

