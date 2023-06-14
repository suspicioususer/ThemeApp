package com.example.themeapp.ui.navigation.components

import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.themeapp.ui.navigation.data.BottomNavigationModel
import com.example.themeapp.ui.theme.customColors

@Composable
fun BottomNavigationBar(navController: NavController) {
    val items = listOf(BottomNavigationModel.A, BottomNavigationModel.B, BottomNavigationModel.C)
    BottomNavigation(backgroundColor = MaterialTheme.customColors.bottomNavigationBackgroundColor,
        contentColor = MaterialTheme.customColors.bottomNavigationContentColor) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.iconId), contentDescription = "") },
                label = { Text(text = stringResource(id = item.titleId), fontSize = 10.sp) },
                selectedContentColor = MaterialTheme.customColors.bottomNavigationContentColor,
                unselectedContentColor = MaterialTheme.customColors.bottomNavigationContentColor.copy(alpha = 0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.navigationPath,
                onClick = {
                    navController.navigate(item.navigationPath) {
                        navController.graph.startDestinationRoute?.let { navigationPath ->
                            popUpTo(navigationPath) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}