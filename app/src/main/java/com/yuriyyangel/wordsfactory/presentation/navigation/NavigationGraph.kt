package com.yuriyyangel.wordsfactory.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.rememberNavController
import com.yuriyyangel.dictionary_feature.navigation.dictionaryGraph
import com.yuriyyangel.dictionary_feature.navigation.navigateToDictionary
import com.yuriyyangel.onboarding.ui.screen.navigation.navigateToOnboarding
import com.yuriyyangel.onboarding.ui.screen.navigation.onBoardingGraph
import ru.yangel.auth_feature.presentation.navigation.LOGIN_ROUTE
import ru.yangel.auth_feature.presentation.navigation.authGraph
import ru.yangel.auth_feature.presentation.navigation.navigateToLogin
import ru.yangel.auth_feature.presentation.navigation.navigateToRegistration
import ru.yangel.core.navigation.NavigationRoutes
import ru.yangel.splash_feature.presentation.navigation.splashGraph

@Composable
fun NavigationGraph(startDestination: String = NavigationRoutes.SplashRoute.route) {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = startDestination) {
        splashGraph(
            onNavigateToRegistration = {
                navController.navigateToRegistration(
                    clearBackStack = true, popBackStackRoute = NavigationRoutes.SplashRoute.route
                )
            },
            onNavigateToHome = {
                navController.navigateToDictionary(
                    clearBackStack = true, popBackStackRoute = NavigationRoutes.SplashRoute.route
                )
            },
            onNavigateToOnboarding = {
                navController.navigateToOnboarding(
                    clearBackStack = true, popBackStackRoute = NavigationRoutes.SplashRoute.route
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
        onBoardingGraph(
            onNavigateToRegistration = {
                navController.navigateToRegistration(
                    clearBackStack = true,
                    popBackStackRoute = NavigationRoutes.OnboardingRoute.route
                )
            }
        )

    }
}


