package com.yuriyyangel.onboarding

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun OnBoardingScreen() {
    val selectedPage = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(
        initialPage = selectedPage.value,
        initialPageOffsetFraction = 0f
    ) {
        3
    }

    HorizontalPager(state = pagerState) { page ->
        when (page) {
            0 -> FirstPageScreen(message = "0")
            1 -> FirstPageScreen(message = "1")
            2 -> FirstPageScreen(message = "2")
        }

    }
}


@Composable
fun FirstPageScreen(message: String) {
    Column(modifier = Modifier
        .fillMaxSize()
        .background(Color.White)) {
        Text(text = message)

    }
}