package com.yuriyyangel.dictionary_feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import com.yuriyyangel.dictionary_feature.DictionaryScreen
import ru.yangel.core.navigation.NavigationRoutes

fun NavController.navigateToDictionary(
    clearBackStack: Boolean = false,
    popBackStackRoute: String? = null
) {
    navigate(NavigationRoutes.DictionaryRoute.route) {
        if (clearBackStack) {
            popBackStackRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}


fun NavGraphBuilder.dictionaryGraph() {
    composable(NavigationRoutes.DictionaryRoute.route) {
        DictionaryScreen()
    }

}