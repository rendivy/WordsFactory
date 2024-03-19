package com.keyinc.dictionary_uikit.components.buttons


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.R

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = stringResource(id = R.string.default_button_text)
) {
    Button(onClick = onClick, modifier = modifier
        .fillMaxWidth()
        .padding(16.dp)) {
        Text(text = text, modifier = Modifier.padding(16.dp))
    }
}


@Composable
@Preview(showBackground = true)
fun AccentButtonPreview() {
    Column(modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally) {
        AccentButton()
        AccentButton()
        AccentButton()
        AccentButton()
        AccentButton()
        AccentButton()
        AccentButton()
        AccentButton()
    }
}