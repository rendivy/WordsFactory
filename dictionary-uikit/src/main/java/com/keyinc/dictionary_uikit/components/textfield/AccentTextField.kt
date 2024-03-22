package com.keyinc.dictionary_uikit.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.ParagraphMedium


@Composable
fun AccentTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    placeHolder: @Composable (() -> Unit)? = null,
    singleLine: Boolean = true,
    error: String? = null,
    onValueChange: (String) -> Unit = {},
    visualTransformation: VisualTransformation = VisualTransformation.None,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val outlinedColor = if (error != null) Color.Red else InkGray
    val containerColor = if (error != null) Color.Red.copy(alpha = 0.1f) else Color.White
    BasicTextField(
        modifier = Modifier
            .background(
                color = containerColor,
                shape = RoundedCornerShape(PaddingMedium)
            )
            .border(
                width = 1.dp,
                color = outlinedColor,
                shape = RoundedCornerShape(PaddingMedium)
            )
            .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onValueChange,
        visualTransformation = visualTransformation,
        textStyle = ParagraphMedium,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        enabled = true,
        cursorBrush = SolidColor(InkGray),
        decorationBox = @Composable { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(PaddingMedium),
            ) {
                if (textFieldValue.isEmpty()) {
                    placeHolder?.invoke()
                }
                innerTextField()
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun AccentTextFieldPreview() {
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        AccentTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
            placeHolder = {
                Text(
                    text = stringResource(id = R.string.password_place_holder),
                    style = ParagraphMedium,
                    color = InkGray
                )
            }
        )
    }
}

