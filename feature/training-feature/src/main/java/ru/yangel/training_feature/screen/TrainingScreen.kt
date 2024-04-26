package ru.yangel.training_feature.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animate
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandIn
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.theme.Display2
import com.keyinc.dictionary_uikit.theme.ErrorColor
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import com.keyinc.dictionary_uikit.theme.SecondaryColor
import com.keyinc.dictionary_uikit.theme.SuccessColor
import com.keyinc.dictionary_uikit.theme.WarningColor
import kotlinx.coroutines.delay
import ru.yangel.training_feature.R
import ru.yangel.training_feature.viewmodel.TrainingState
import ru.yangel.training_feature.viewmodel.TrainingViewModel

@Composable
fun TrainingScreen(trainingViewModel: TrainingViewModel = hiltViewModel()) {
    val context = LocalContext.current
    val trainingState by trainingViewModel.trainingState.collectAsStateWithLifecycle()
    val wordCount by trainingViewModel.wordCount.collectAsStateWithLifecycle()
    val name = context.getString(R.string.training_label, wordCount)
    val startIndex = name.indexOf(wordCount.toString())
    val endIndex = startIndex + wordCount.toString().length

    if (wordCount < 3) {
        TrainingNoWordPlaceHolder()
        return
    }

    val annotatedString = buildAnnotatedString {
        append(name.substring(0, startIndex))
        withStyle(style = SpanStyle(color = PrimaryColor)) {
            append(name.substring(startIndex, endIndex))
        }
        append(name.substring(endIndex, name.length))
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = annotatedString, style = Heading1, textAlign = TextAlign.Center)
            Spacer(modifier = Modifier.padding(PaddingLarge))
            Text(
                text = stringResource(id = R.string.start_the_training),
                style = Heading1
            )
        }
        AnimatedVisibility(
            visible = trainingState is TrainingState.Initial,
            enter = fadeIn() + expandIn(),
            exit = fadeOut() + shrinkOut()
        ) {
            AccentButton(
                modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium),
                onClick = { trainingViewModel.startTraining() },
            )
        }

        AnimatedVisibility(
            visible = trainingState is TrainingState.Start,
            enter = fadeIn() + expandIn(),
            exit = fadeOut() + shrinkOut()
        ) {
            LoadingScreen()
        }
    }
}

@Composable
@Preview(showBackground = true)
fun TrainingNoWordPlaceHolder() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = stringResource(id = R.string.add_some_word),
                style = Heading1,
                textAlign = TextAlign.Center
            )
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
        modifier = Modifier.fillMaxWidth(),
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
