package com.keyinc.dictionary_uikit.components.bottomNavigation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.components.noRippleClickable
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
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
    val currentRoute= rememberSaveable { mutableStateOf("dictionary") }
    val screens = listOf(
        BottomBarRoutes.Dictionary,
        BottomBarRoutes.Train,
        BottomBarRoutes.Video,
    )
    BottomAppBar(
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
                currentRoute = currentRoute,
            )
        }
    }
}


@Composable
fun RowScope.AddItem(
    screen: BottomBarRoutes,
    currentRoute: MutableState<String>,
) {
    val color = if (currentRoute.value == screen.route) PrimaryColor else InkGray
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .weight(1f)
            .noRippleClickable {
                currentRoute.value = screen.route
            }) {
        Icon(
            imageVector = ImageVector.vectorResource(screen.icon),
            tint = color,
            contentDescription = "Navigation icon",
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            modifier = Modifier,
            text = screen.title,
            color = color,
            style = ParagraphMedium,
            textAlign = TextAlign.Center
        )
    }
}




