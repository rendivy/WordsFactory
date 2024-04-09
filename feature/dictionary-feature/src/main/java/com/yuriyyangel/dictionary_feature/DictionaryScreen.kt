package com.yuriyyangel.dictionary_feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.keyinc.dictionary_uikit.components.buttons.AccentButton
import com.keyinc.dictionary_uikit.components.cards.MeaningCard
import com.keyinc.dictionary_uikit.components.noRippleClickable
import com.keyinc.dictionary_uikit.components.textfield.SearchTextField
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.Heading2
import com.keyinc.dictionary_uikit.theme.InkDark
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSemiMeduim
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import com.yuriyyangel.dictionary_feature.utils.convertToPhoneticFormat
import com.yuriyyangel.dictionary_feature.viewmodel.DictionaryState
import com.yuriyyangel.dictionary_feature.viewmodel.DictionaryViewModel
import ru.yangel.dictionary_data.model.WordDTO


@Composable
@Preview(showBackground = true, device = "id:Nexus 4")
fun DictionaryScreen(viewModel: DictionaryViewModel = hiltViewModel()) {
    val dictionaryState = viewModel.dictionaryState.collectAsStateWithLifecycle()
    val dictionaryUiState by viewModel.dictionaryUiState.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            Column(
                modifier = Modifier
                    .background(Color.White)
                    .padding(
                        top = PaddingSemiMeduim,
                        bottom = PaddingSemiMeduim,
                        end = PaddingMedium,
                        start = PaddingMedium
                    )
            ) {
                SearchTextField(
                    modifier = Modifier.fillMaxWidth(),
                    textFieldValue = dictionaryUiState.word,
                    onValueChange = { viewModel.onChangeWord(it) },
                    onClick = { viewModel.getWordWithDefinition() }
                )
            }
        },
    ) {
        when (dictionaryState.value) {
            is DictionaryState.Initial -> DictionaryNoWordScreen(modifier = Modifier.padding(it))
            is DictionaryState.Error -> {}
            is DictionaryState.Loading -> CircularProgressIndicator()
            is DictionaryState.Success -> {
                val content = ((dictionaryState.value) as DictionaryState.Success).data

                WordScreen(
                    word = content[0],
                    modifier = Modifier.padding(it),
                    viewModel = viewModel
                )
            }
        }
    }
}


@Composable
private fun DictionaryNoWordScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_word_ic),
            contentDescription = null,
        )
        Text(
            text = stringResource(id = R.string.no_word),
            modifier = Modifier.padding(top = PaddingLarge),
            style = Heading1
        )
        Text(
            text = stringResource(id = R.string.no_word_desc),
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = PaddingSmall, bottom = PaddingLarge),
            style = ParagraphMedium
        )
    }
}


@Composable
private fun WordScreen(
    modifier: Modifier = Modifier,
    onSaveWord: () -> Unit = {},
    viewModel: DictionaryViewModel,
    word: WordDTO
) {
    val context = LocalContext.current
    val audioIsPlaying = remember { mutableStateOf(false) }
    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(Color.White),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        item {
            Row(
                modifier = Modifier
                    .padding(start = 16.dp, bottom = 16.dp)
                    .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Start
            ) {
                Text(
                    text = word.word.replaceFirstChar { char -> char.titlecaseChar() },
                    style = Heading1,
                )
                Row(modifier = Modifier.padding(start = 16.dp)) {
                    word.phonetics.forEach {
                        Text(
                            text = it.text.convertToPhoneticFormat(),
                            style = ParagraphMedium,
                            color = PrimaryColor,
                            modifier = Modifier
                                .padding(end = 4.dp)
                                .noRippleClickable {
                                    if (it.audio.isNotEmpty() && !audioIsPlaying.value) {
                                        viewModel.playAudio(
                                            url = it.audio,
                                            onCompleteListener = { audioIsPlaying.value = false },
                                            onStartListener = { audioIsPlaying.value = true }
                                        )
                                    }
                                }
                        )
                    }

                    Row(
                        modifier = Modifier
                            .padding(start = 16.dp, bottom = 16.dp)
                            .fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.Start
                    ) {
                        Text(text = "Part of Speech:", style = Heading2)
                        Text(
                            text = word.meanings[0].partOfSpeech.replaceFirstChar { char -> char.titlecaseChar() },
                            style = ParagraphMedium,
                            color = InkDark,
                            modifier = Modifier.padding(start = 16.dp)
                        )
                    }
                    Text(
                        text = "Meanings :",
                        style = Heading2,
                        modifier = Modifier.padding(start = 16.dp, bottom = 16.dp)
                    )
                }
            }
        }

        items(word.meanings[0].definitions.size) {
            if (word.meanings[0].definitions[it].definition != null)
                MeaningCard(
                    meaningText = word.meanings[0].definitions[it].definition,
                    exampleText = word.meanings[0].definitions[it].example
                )

        }
        item {
            AccentButton(
                text = stringResource(id = R.string.add_word),
                onClick = onSaveWord,
                modifier = Modifier.padding(16.dp)
            )
        }
    }
}