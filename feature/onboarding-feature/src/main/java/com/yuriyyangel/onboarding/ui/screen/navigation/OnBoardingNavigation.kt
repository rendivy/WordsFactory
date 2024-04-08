package com.yuriyyangel.onboarding.ui.screen.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yuriyyangel.onboarding.ui.screen.OnBoardingScreen
import ru.yangel.core.navigation.NavigationRoutes

fun NavController.navigateToOnboarding(
    clearBackStack: Boolean = false,
    popBackStackRoute: String? = null
) {
    navigate(NavigationRoutes.OnboardingRoute.route) {
        if (clearBackStack) {
            popBackStackRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}


fun NavGraphBuilder.onBoardingGraph(onNavigateToRegistration: () -> Unit) {
    composable(NavigationRoutes.OnboardingRoute.route) {
        OnBoardingScreen(onNavigateToRegistration = onNavigateToRegistration)
    }

}