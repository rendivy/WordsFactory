package com.keyinc.dictionary_uikit.components.buttons

import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.components.rippleEffect.NoRippleTheme
import com.keyinc.dictionary_uikit.theme.ButtonSmall
import com.keyinc.dictionary_uikit.theme.InkDarkGray


@Composable
fun CustomTextButton(
    modifier: Modifier = Modifier,
    text: String = stringResource(id = R.string.skip_button_text),
    onClick: () -> Unit = {}
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        TextButton(onClick = onClick, modifier = modifier) {
            Text(text = text, style = ButtonSmall, color = InkDarkGray)
        }
    }
}


@Composable
@Preview(showBackground = true)
private fun CustomTextButtonPreview() {
    CustomTextButton()
}
