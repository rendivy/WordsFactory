package com.yuriyyangel.dictionary_feature.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.keyinc.dictionary_uikit.components.bottomNavigation.BottomBar
import com.keyinc.dictionary_uikit.components.bottomNavigation.BottomBarRoutes
import com.yuriyyangel.dictionary_feature.DictionaryScreen
import ru.yangel.core.navigation.NavigationRoutes
import ru.yangel.training_feature.screen.TrainingScreen
import ru.yangel.video_feature.VideoScreen

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
        MainScreen()
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = { BottomBar(navController = navController) }
    ) {
        BottomNavGraph(navController = navController, modifier = Modifier.padding(it))
    }
}

@Composable
fun BottomNavGraph(navController: NavHostController, modifier: Modifier = Modifier) {
    NavHost(
        navController = navController,
        modifier = modifier,
        startDestination = BottomBarRoutes.Dictionary.route
    ) {
        composable(route = BottomBarRoutes.Dictionary.route) {
            DictionaryScreen()
        }
        composable(route = BottomBarRoutes.Train.route) {
            TrainingScreen()
        }
        composable(route = BottomBarRoutes.Video.route) {
            VideoScreen()
        }
    }
}
