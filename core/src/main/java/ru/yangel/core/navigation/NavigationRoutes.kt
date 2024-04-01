package ru.yangel.core.navigation

sealed class NavigationRoutes(val route: String) {
    data object DictionaryRoute : NavigationRoutes("dictionary")
}