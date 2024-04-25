package ru.yangel.training_feature.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import ru.yangel.training_feature.R

@Composable
fun TrainingScreen(wordCount: Int) {
    val context = LocalContext.current
    val name = context.getString(R.string.training_label, wordCount)
    val startIndex = name.indexOf(wordCount.toString())
    val endIndex = startIndex + wordCount.toString().length

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
        AccentButton(
            modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium),
        )
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


