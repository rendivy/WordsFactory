package ru.yangel.training_feature.screen

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.InkDark
import com.keyinc.dictionary_uikit.theme.InkDarkGray
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import ru.yangel.training_feature.R

@Composable
@Preview(showBackground = true)
fun TrainingEndScreen(correctWord: Int = 1, incorrectWord: Int = 9, onClick: () -> Unit = {}) {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(painter = painterResource(id = R.drawable.training_ic), contentDescription = null)
        Text(
            text = "Training is finished",
            style = Heading1,
            color = InkDark,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = PaddingLarge)
        )
        Text(
            text = "Correct: $correctWord",
            style = ParagraphMedium,
            color = InkDarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = PaddingSmall)
        )
        Text(
            text = "Incorrect: $incorrectWord",
            style = ParagraphMedium,
            color = InkDarkGray,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = PaddingSmall)
        )
        AccentButton(
            modifier = Modifier.padding(
                top = PaddingLarge,
                start = PaddingLarge,
                end = PaddingLarge
            ),
            onClick = onClick,
            text = "Again"
        )
    }
}