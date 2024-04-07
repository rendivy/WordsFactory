package com.yuriyyangel.onboarding.ui.screen

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.buttons.CustomTextButton
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.InkDarkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSemiMeduim
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import com.keyinc.dictionary_uikit.theme.SecondaryColor
import com.yuriyyangel.onboarding.R
import com.yuriyyangel.onboarding.ui.viewmodel.OnBoardingViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalFoundationApi::class)
@Composable
@Preview(showBackground = true)
fun OnBoardingScreen(
    viewModel: OnBoardingViewModel = hiltViewModel(),
    onNavigateToRegistration: () -> Unit = {}
) {
    val selectedPage = remember { mutableStateOf(0) }
    val pagerState = rememberPagerState(
        initialPage = selectedPage.value,
        initialPageOffsetFraction = 0f
    ) { 3 }
    val scope = rememberCoroutineScope()
    val buttonMessage = when (pagerState.currentPage) {
        0 -> stringResource(id = R.string.next)
        1 -> stringResource(id = R.string.next)
        2 -> stringResource(id = R.string.start)
        else -> ""
    }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ) {
        CustomTextButton(modifier = Modifier.align(Alignment.TopEnd))
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HorizontalPager(
                state = pagerState,
                modifier = Modifier.padding(end = PaddingMedium, top = PaddingSemiMeduim)
            ) { page ->
                when (page) {
                    0 -> {
                        OnBoardingContent(
                            label = stringResource(id = R.string.learn_anytime),
                            logo = painterResource(id = R.drawable.on_boarding_first),
                            message = stringResource(id = R.string.first_onboarding_desc)
                        )
                    }

                    1 -> {
                        OnBoardingContent(
                            label = stringResource(id = R.string.find_a_course),
                            logo = painterResource(id = R.drawable.second_onboarding),
                            message = stringResource(id = R.string.first_onboarding_desc)
                        )
                    }

                    2 -> {
                        OnBoardingContent(
                            label = stringResource(id = R.string.improve),
                            logo = painterResource(id = R.drawable.third_onboarding),
                            message = stringResource(id = R.string.first_onboarding_desc)
                        )
                    }
                }
            }

            Row(
                Modifier
                    .fillMaxWidth()
                    .padding(bottom = PaddingSmall, top = PaddingMedium),
                horizontalArrangement = Arrangement.Center
            ) {
                repeat(pagerState.pageCount) { iteration ->

                    val targetColor =
                        if (pagerState.currentPage == iteration) SecondaryColor else InkDarkGray
                    val targetSize =
                        if (pagerState.currentPage == iteration) PaddingMedium else 6.dp

                    val color by animateColorAsState(
                        targetColor,
                        animationSpec = tween(durationMillis = 500)
                    )
                    val size by animateDpAsState(
                        targetSize,
                        animationSpec = tween(durationMillis = 500)
                    )

                    Box(
                        modifier = Modifier
                            .padding(PaddingSmall)
                            .clip(CircleShape)
                            .background(color)
                            .height(6.dp)
                            .width(size)
                    )
                }
            }
        }
        AccentButton(
            text = buttonMessage,
            modifier = Modifier
                .padding(bottom = PaddingSemiMeduim, start = PaddingMedium, end = PaddingMedium)
                .fillMaxWidth()
                .align(Alignment.BottomCenter),
            onClick = {
                if (pagerState.currentPage == 2) {
                    viewModel.passOnBoarding()
                    onNavigateToRegistration()
                } else {
                    scope.launch {
                        pagerState.animateScrollToPage(pagerState.currentPage + 1)
                    }
                }

            }
        )
    }
}


@Composable
fun OnBoardingContent(logo: Painter, label: String, message: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(Color.White),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = logo,
            contentDescription = null,
            modifier = Modifier
                .aspectRatio(1.5f)
                .padding(start = PaddingMedium, end = PaddingMedium)
        )
        Spacer(modifier = Modifier.size(PaddingMedium))
        Text(
            text = label,
            textAlign = TextAlign.Center,
            style = Heading1
        )
        Spacer(modifier = Modifier.size(PaddingSmall))
        Text(
            text = message,
            modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium),
            textAlign = TextAlign.Center,
            style = ParagraphMedium
        )
    }
}


