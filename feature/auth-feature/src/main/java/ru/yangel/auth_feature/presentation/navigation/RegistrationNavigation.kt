package ru.yangel.auth_feature.presentation.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yangel.auth_feature.presentation.registration.SignUpScreen


val REGISTRATION_ROUTE = NavigationRoutes.Registration.route
val LOGIN_ROUTE = NavigationRoutes.Login.route

fun NavController.navigateToRegistration() {
    navigate(REGISTRATION_ROUTE)
}

fun NavGraphBuilder.authGraph(onSignUpClicked: () -> Unit = {}) {
    composable(route = REGISTRATION_ROUTE) {
        SignUpScreen()
    }
    composable(route = LOGIN_ROUTE) {
        throw NotImplementedError("Login screen is not implemented yet")
    }

}