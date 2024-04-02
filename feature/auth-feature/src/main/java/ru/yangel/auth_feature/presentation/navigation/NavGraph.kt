package ru.yangel.auth_feature.presentation.navigation

import android.app.appsearch.AppSearchResult.RESULT_OK
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContract
import androidx.activity.result.contract.ActivityResultContracts
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yangel.auth_feature.presentation.login.screen.SignInScreen
import ru.yangel.auth_feature.presentation.registration.SignUpScreen


val REGISTRATION_ROUTE = NavigationRoutes.Registration.route
val LOGIN_ROUTE = NavigationRoutes.Login.route

fun NavController.navigateToRegistration(
    clearBackStack: Boolean = false,
    popBackStackRoute: String? = null
) {
    navigate(REGISTRATION_ROUTE) {
        if (clearBackStack) {
            popBackStackRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}

fun NavController.navigateToLogin(
    clearBackStack: Boolean = false,
    popBackStackRoute: String? = null
) {
    navigate(LOGIN_ROUTE) {
        if (clearBackStack) {
            popBackStackRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}


fun NavGraphBuilder.authGraph(
    onSignUpClicked: () -> Unit = {},
    onNavigateToHome: () -> Unit = {},
    onNavigateToLogin: () -> Unit = {}
) {
    composable(route = REGISTRATION_ROUTE) {
        SignUpScreen(onNavigateToLogin = onNavigateToLogin, onNavigateToHome)
    }
    composable(route = LOGIN_ROUTE) {
        SignInScreen(
            navigateToHome = onNavigateToHome
        )
    }

}