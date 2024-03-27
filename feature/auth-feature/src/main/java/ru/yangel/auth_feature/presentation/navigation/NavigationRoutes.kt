package ru.yangel.auth_feature.presentation.navigation

internal sealed class NavigationRoutes(val route: String) {
    data object Registration : NavigationRoutes("registration")
    data object Login : NavigationRoutes("login")
}