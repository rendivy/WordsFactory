package com.yuriyyangel.wordsfactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavOptions
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.yangel.auth_feature.presentation.navigation.authGraph
import ru.yangel.auth_feature.presentation.navigation.navigateToLogin
import ru.yangel.auth_feature.presentation.navigation.navigateToRegistration
import ru.yangel.splash_feature.presentation.navigation.SPLASH_ROUTE
import ru.yangel.splash_feature.presentation.navigation.splashGraph

@Composable
fun NavigationGraph(startDestination: String = SPLASH_ROUTE) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        splashGraph(
            onNavigateToRegistration = {
                navController.navigateToRegistration(
                    clearBackStack = true, popBackStackRoute = SPLASH_ROUTE
                )
            },
            onNavigateToLogin = {
                navController.navigateToLogin(
                    clearBackStack = true, popBackStackRoute = SPLASH_ROUTE
                )
            }
        )
        authGraph()
    }
}


