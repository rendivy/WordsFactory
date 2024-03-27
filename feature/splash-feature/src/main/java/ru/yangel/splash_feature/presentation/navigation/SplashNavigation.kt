package ru.yangel.splash_feature.presentation.navigation


import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yangel.splash_feature.presentation.SplashScreen

const val SPLASH_ROUTE = "splash"


fun NavGraphBuilder.splashGraph(
    onNavigateToRegistration: () -> Unit,
    onNavigateToLogin: () -> Unit
) {
    composable(route = SPLASH_ROUTE) {
        SplashScreen(
            onNavigateToRegistration = onNavigateToRegistration,
            onNavigateToLogin = onNavigateToLogin
        )
    }

}