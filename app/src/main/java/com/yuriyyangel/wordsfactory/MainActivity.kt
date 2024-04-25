package com.yuriyyangel.wordsfactory

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keyinc.dictionary_uikit.theme.Display2
import com.keyinc.dictionary_uikit.theme.ErrorColor
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import com.keyinc.dictionary_uikit.theme.SecondaryColor
import com.keyinc.dictionary_uikit.theme.SuccessColor
import com.keyinc.dictionary_uikit.theme.WarningColor
import com.yuriyyangel.wordsfactory.presentation.navigation.NavigationGraph
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay


@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavigationGraph()
        }
    }
}


@Composable
@Preview(showBackground = true)
fun LoadingScreen() {
    var progress by remember { mutableStateOf(0f) }

    val state = when {
        progress < 0.2f -> "5"
        progress < 0.4f -> "4"
        progress < 0.6f -> "3"
        progress < 0.8f -> "2"
        progress < 1.0f -> "1"
        else -> "G0!"
    }

    val color = when {
        progress < 0.2f -> PrimaryColor
        progress < 0.4f -> SecondaryColor
        progress < 0.6f -> SuccessColor
        progress < 0.8f -> WarningColor
        progress < 1.0f -> ErrorColor
        else -> PrimaryColor
    }

    LaunchedEffect(Unit) {
        while (progress < 1.2f) {
            animate(
                initialValue = progress,
                targetValue = progress + 0.20f,
                animationSpec = tween(1000)
            ) { value, _ ->
                progress = value
            }
            delay(1000L)
        }
    }

    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier.fillMaxWidth(),
            contentAlignment = Alignment.Center
        ) {
            CircularProgressIndicator(
                progress = { progress },
                modifier = Modifier.size(140.dp),
                strokeWidth = 7.dp,
                color = PrimaryColor,
                strokeCap = StrokeCap.Round
            )
            Text(
                text = state,
                style = Display2,
                color = color,
                fontSize = 56.sp,
            )
        }


    }
}


