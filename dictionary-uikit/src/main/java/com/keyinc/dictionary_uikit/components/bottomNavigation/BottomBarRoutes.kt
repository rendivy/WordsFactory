package com.keyinc.dictionary_uikit.components.bottomNavigation

import com.keyinc.dictionary_uikit.R

sealed class BottomBarRoutes(
    val route: String,
    val title: String,
    val icon: Int,

    ) {
    data object Dictionary : BottomBarRoutes(
        route = "dictionary",
        title = "Dictionary",
        icon = R.drawable.ic_dictionary
    )

    data object Train : BottomBarRoutes(
        route = "training",
        title = "Training",
        icon = R.drawable.ic_train
    )

    data object Video : BottomBarRoutes(
        route = "video",
        title = "Video",
        icon = R.drawable.ic_video
    )


}