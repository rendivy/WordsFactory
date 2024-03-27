package com.keyinc.dictionary_uikit.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.components.noRippleClickable
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.ParagraphMedium

@Composable
fun SearchTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    singleLine: Boolean = true,
    onClick: () -> Unit = {},
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    BasicTextField(
        modifier = Modifier
            .background(
                color = Color.White,
                shape = RoundedCornerShape(PaddingMedium)
            )
            .border(
                width = 1.dp,
                color = InkGray,
                shape = RoundedCornerShape(PaddingMedium)
            )
            .fillMaxWidth(),
        value = textFieldValue,
        onValueChange = onValueChange,
        textStyle = ParagraphMedium,
        keyboardOptions = keyboardOptions,
        singleLine = singleLine,
        enabled = true,
        cursorBrush = SolidColor(InkGray),
        decorationBox = @Composable { innerTextField ->
            Box(
                modifier = modifier
                    .fillMaxWidth()
                    .padding(
                        start = PaddingMedium,
                        end = PaddingMedium,
                        top = 18.dp,
                        bottom = 18.dp
                    )
            ) {
                innerTextField()
                Icon(
                    imageVector = ImageVector.vectorResource(id = R.drawable.ic_find),
                    modifier = Modifier
                        .noRippleClickable { onClick() }
                        .size(18.dp)
                        .align(Alignment.CenterEnd),
                    contentDescription = null,
                )

            }
        }
    )
}


@Composable
@Preview(showBackground = true)
private fun SearchTextFieldPreview() {
    var find by remember { mutableStateOf("") }
    SearchTextField(
        textFieldValue = find,
        onValueChange = { find = it },
        onClick = { find = "lol"}
    )
}
