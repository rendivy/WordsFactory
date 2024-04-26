package ru.yangel.training_feature.screen

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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.buttons.CustomProgressBar
import com.keyinc.dictionary_uikit.components.buttons.OutlinedQuestionButton
import com.keyinc.dictionary_uikit.theme.ButtonQuestion
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall


@Composable
@Preview(showBackground = true)
fun QuestionScreen() {
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
                text = "1 of 3",
                style = ButtonQuestion,
                color = InkGray,
            )
            Text(
                text = "The practice or skill of preparing food by combining, mixing, and heating ingredients.",
                modifier = Modifier.padding(top = PaddingSmall),
                style = Heading1,
                textAlign = TextAlign.Center
            )
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton()
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton()
            Spacer(modifier = Modifier.padding(PaddingMedium))
            OutlinedQuestionButton()
            Spacer(modifier = Modifier.padding(PaddingMedium))
            AccentButton(modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium))
            Spacer(modifier = Modifier.height(PaddingMedium))
            CustomProgressBar(
                Modifier
                    .clip(shape = RoundedCornerShape(PaddingSmall))
                    .height(6.dp),
                width = 400.dp,
                Color.Gray,
                Brush.horizontalGradient(listOf(Color(0xffF29F3F), Color(0xffEF4949))),
                65,
            )
        }
    }


}