package com.keyinc.dictionary_uikit.components.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PrimaryColor


@Composable
@Preview(showBackground = true)
fun CustomBottomNavigationBar() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Bottom,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BottomBar()
    }
}


@Composable
fun BottomBar() {
    val screens = listOf(
        BottomBarRoutes.Dictionary,
        BottomBarRoutes.Train,
        BottomBarRoutes.Video,
    )
    NavigationBar(
        modifier = Modifier
            .fillMaxWidth()
            .border(
                1.dp,
                InkGray,
                RoundedCornerShape(PaddingMedium, PaddingMedium, 0.dp, 0.dp)
            )
            .clip(RoundedCornerShape(PaddingMedium, PaddingMedium, 0.dp, 0.dp)),
        containerColor = Color.White,
    ) {
        screens.forEach { screen ->
            AddItem(
                screen = screen,
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarRoutes
) {
    NavigationBarItem(
        interactionSource = NoRippleInteractionSource(),
        label = {
            Text(text = screen.title)
        },
        icon = {
            Icon(
                imageVector = ImageVector.vectorResource(screen.icon),
                contentDescription = "Navigation icon",
            )
        },
        selected = screen.route == "Dictionary",
        colors = NavigationBarItemDefaults.colors(
            unselectedIconColor = InkGray,
            selectedIconColor = PrimaryColor,
            unselectedTextColor = InkGray,
            selectedTextColor = PrimaryColor,
            indicatorColor = Color.Transparent
        ),
        onClick = {

        }
    )
}