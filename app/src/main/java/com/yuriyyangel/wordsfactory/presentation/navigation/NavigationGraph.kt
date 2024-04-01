package com.yuriyyangel.wordsfactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yuriyyangel.dictionary_feature.navigation.dictionaryGraph
import com.yuriyyangel.dictionary_feature.navigation.navigateToDictionary
import ru.yangel.auth_feature.presentation.navigation.LOGIN_ROUTE
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
            onNavigateToHome = {
                navController.navigateToDictionary(
                    clearBackStack = true, popBackStackRoute = SPLASH_ROUTE
                )
            }
        )
        authGraph(
            onNavigateToLogin = {
                navController.navigateToLogin(
                    clearBackStack = false
                )
            },
            onNavigateToHome = {
                navController.navigateToDictionary(
                    clearBackStack = true, popBackStackRoute = LOGIN_ROUTE
                )
            }
        )
        dictionaryGraph()

    }
}


