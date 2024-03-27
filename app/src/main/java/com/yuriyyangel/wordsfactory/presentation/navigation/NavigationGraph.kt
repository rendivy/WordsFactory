package com.yuriyyangel.wordsfactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import ru.yangel.auth_feature.presentation.navigation.authGraph
import ru.yangel.auth_feature.presentation.navigation.navigateToRegistration
import ru.yangel.splash_feature.presentation.navigation.SPLASH_ROUTE
import ru.yangel.splash_feature.presentation.navigation.splashGraph

@Composable
fun NavigationGraph(startDestination: String = SPLASH_ROUTE) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        splashGraph(
            onNavigateToRegistration = { navController.navigateToRegistration() },
            onNavigateToLogin = { navController.navigateToRegistration() }
        )
        authGraph()
    }
}