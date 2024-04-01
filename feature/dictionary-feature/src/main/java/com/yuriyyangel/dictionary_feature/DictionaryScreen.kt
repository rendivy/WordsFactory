package com.yuriyyangel.dictionary_feature

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import com.keyinc.dictionary_uikit.components.bottomNavigation.BottomBar
import com.keyinc.dictionary_uikit.components.textfield.SearchTextField
import com.keyinc.dictionary_uikit.theme.Heading1
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSemiMeduim
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium


@Composable
@Preview(
    showBackground = true,
    device = "id:Nexus 4"
)
fun DictionaryScreen() {
    Scaffold(topBar = {
        Column(
            modifier = Modifier
                .padding(
                    top = PaddingSemiMeduim,
                    bottom = PaddingSemiMeduim,
                    end = PaddingMedium,
                    start = PaddingMedium
                )
        ) {
            SearchTextField(modifier = Modifier.fillMaxWidth())
        }

    },
        bottomBar = {
            BottomBar()
        }) {
        DictionaryNoWordScreen(modifier = Modifier.padding(it))
    }

}


@Composable
private fun DictionaryNoWordScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.no_word_ic),
            modifier = Modifier.weight(1f),
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