package ru.yangel.training_feature.screen

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.buttons.CustomProgressBar
import com.keyinc.dictionary_uikit.components.buttons.OutlinedQuestionButton
import com.keyinc.dictionary_uikit.theme.ButtonQuestion
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import ru.yangel.dictionary_data.model.QuestionDTO
import ru.yangel.training_feature.viewmodel.QuestionState
import ru.yangel.training_feature.viewmodel.QuestionViewModel


@Composable
@Preview(showBackground = true)
fun QuestionScreen(questionViewModel: QuestionViewModel = hiltViewModel()) {

    val questionState = questionViewModel.questionState.collectAsState()
    val currentQuestionIndex = questionViewModel.currentQuestionIndex.collectAsState()
    val questionList = questionViewModel.questionList.collectAsState()

    when (questionState.value) {
        is QuestionState.Question -> {
            AnimatedVisibility(
                visible = questionState.value is QuestionState.Question,
                enter = fadeIn(animationSpec = tween(durationMillis = 500)),
                exit = fadeOut(animationSpec = tween(durationMillis = 500)),
            ) {
                QuestionContent(
                    questionDTO = questionList.value[currentQuestionIndex.value],
                    questionSize = questionList.value.size,
                    currentQuestionIndex = currentQuestionIndex.value,
                    onClick = { word -> questionViewModel.onAnswerSelected(word) },
                    onAccentClick = { questionViewModel.resetState() }
                )
            }
        }

        is QuestionState.WordClicked -> {
            questionViewModel.resetToQuestion()
        }

        is QuestionState.Initial -> {
            questionViewModel.onInitial()
        }

        is QuestionState.QuestionAnswered -> {
            TrainingEndScreen(
                correctWord = (questionState.value as QuestionState.QuestionAnswered).correct,
                incorrectWord = (questionState.value as QuestionState.QuestionAnswered).incorrect,
                onClick = { questionViewModel.resetState() }
            )
        }


    }

}


@Composable
fun QuestionContent(
    questionDTO: QuestionDTO,
    questionSize: Int,
    currentQuestionIndex: Int,
    onClick: (String) -> Unit = {},
    onAccentClick: () -> Unit = {}
) {
    val coroutineScope = rememberCoroutineScope()
    val job = remember { mutableStateOf<Job?>(null) }
    val progress = remember { mutableStateOf(100) }

    LaunchedEffect(currentQuestionIndex) {
        job.value?.cancel()
        job.value = coroutineScope.launch {
            for (i in 5 downTo 0) {
                delay(1000)
                progress.value = i * 20
            }
            onClick("")
        }
    }

    Box(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(16.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "${currentQuestionIndex + 1} of $questionSize",
                style = ButtonQuestion,
                color = InkGray,
            )
            Text(
                text = questionDTO.question,
                modifier = Modifier.padding(top = PaddingSmall),
                style = Heading1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton(
                text = questionDTO.answers[0],
                onClick = {
                    onClick(questionDTO.answers[0])
                },
            )
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton(
                text = questionDTO.answers[1],
                onClick = { onClick(questionDTO.answers[1]) },
            )
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton(
                text = questionDTO.answers[2],
                onClick = { onClick(questionDTO.answers[2]) },
            )
            Spacer(modifier = Modifier.height(PaddingMedium))
            AccentButton(
                text = "Again",
                modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium),
                onClick = { onAccentClick() },
            )
            Spacer(modifier = Modifier.height(PaddingLarge))
            CustomProgressBar(
                Modifier
                    .clip(shape = RoundedCornerShape(PaddingSmall))
                    .height(6.dp),
                width = 400.dp,
                Color.Gray,
                Brush.horizontalGradient(listOf(Color(0xffF29F3F), Color(0xffEF4949))),
                progress.value,
            )
        }
    }
}