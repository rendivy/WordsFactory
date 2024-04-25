package ru.yangel.training_feature.navigation

import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable
import ru.yangel.training_feature.screen.TrainingScreen

private const val TRAINING_ROUTE = "training"

fun NavController.navigateToTraining(
    clearBackStack: Boolean = false,
    popBackStackRoute: String? = null
) {
    navigate(TRAINING_ROUTE) {
        if (clearBackStack) {
            popBackStackRoute?.let {
                popUpTo(it) {
                    inclusive = true
                }
            }
        }
    }
}




fun NavGraphBuilder.trainingGraph() {
    composable(route = TRAINING_ROUTE) {
        TrainingScreen(wordCount = 10)
    }

}