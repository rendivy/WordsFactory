package com.keyinc.dictionary_uikit.components.buttons


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.ripple.LocalRippleTheme
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.components.rippleEffect.NoRippleTheme
import com.keyinc.dictionary_uikit.theme.ButtonMedium
import com.keyinc.dictionary_uikit.theme.InkDark
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import com.keyinc.dictionary_uikit.theme.SecondaryColor

@Composable
fun AccentButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = stringResource(id = R.string.default_accent_button_text)
) {

    Button(
        onClick = onClick, modifier = modifier
            .fillMaxWidth(),
        colors = ButtonDefaults.buttonColors(containerColor = PrimaryColor),
        shape = RoundedCornerShape(PaddingMedium)
    ) {
        Text(
            text = text,
            modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
            style = ButtonMedium,
            color = Color.White,
        )
    }

}


@Composable
fun OutlinedAccentButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    text: String = stringResource(id = R.string.default_accent_button_text)
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        OutlinedButton(
            onClick = onClick, modifier = modifier
                .fillMaxWidth()
                .border(2.dp, PrimaryColor, RoundedCornerShape(PaddingMedium)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(PaddingMedium)
        ) {
            Text(
                text = text,
                modifier = Modifier.padding(top = 12.dp, bottom = 12.dp),
                style = ButtonMedium,
                color = InkDark,
            )
        }
    }
}


@Composable
@Preview(showBackground = true)
fun IconBackground(
    modifier: Modifier = Modifier,
    painter: Painter = painterResource(id = R.drawable.google_plus)
) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(PaddingSmall))
            .background(color = SecondaryColor)
    ) {
        Image(
            painter = painter,
            modifier = modifier.padding(PaddingSmall),
            contentDescription = null
        )
    }
}

@Composable
fun OutlinedAccentButtonWithIcon(
    modifier: Modifier = Modifier,
    onClick: () -> Unit = {},
    painter: Painter = painterResource(id = R.drawable.google_plus),
    text: String = "Continue with Facebook"
) {
    CompositionLocalProvider(LocalRippleTheme provides NoRippleTheme) {
        OutlinedButton(
            onClick = onClick, modifier = modifier
                .fillMaxWidth()
                .border(2.dp, PrimaryColor, RoundedCornerShape(PaddingMedium)),
            colors = ButtonDefaults.outlinedButtonColors(
                containerColor = Color.Transparent,
            ),
            shape = RoundedCornerShape(PaddingMedium)
        ) {
            Box(modifier = Modifier.fillMaxWidth()) {
                IconBackground(painter = painter)
                Text(
                    text = text,
                    modifier = Modifier
                        .padding(top = 12.dp, bottom = 12.dp)
                        .fillMaxWidth(),
                    style = ButtonMedium,
                    textAlign = TextAlign.Center,
                    color = InkDark,
                )

            }
        }
    }
}

@Composable
@Preview(showBackground = true)
private fun AccentButtonPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        AccentButton(modifier = Modifier.padding(start = PaddingMedium, end = PaddingMedium))
        Spacer(modifier = Modifier.height(PaddingMedium))
        OutlinedAccentButton(
            modifier = Modifier.padding(
                start = PaddingMedium,
                end = PaddingMedium
            )
        )
        Spacer(modifier = Modifier.height(PaddingMedium))
        OutlinedAccentButtonWithIcon(
            modifier = Modifier.padding(
                start = PaddingMedium,
                end = PaddingMedium
            )
        )
    }
}