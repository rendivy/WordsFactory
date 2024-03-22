package com.keyinc.dictionary_uikit.components.textfield

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Create
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.ParagraphMedium


@Composable
fun AccentTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    singleLine: Boolean = true,
    placeHolderValue: String = "",
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
                innerTextField()
                if (textFieldValue.isEmpty()) {
                    Text(
                        text = placeHolderValue,
                        style = ParagraphMedium,
                        color = InkGray
                    )
                }
            }
        }
    )
}


@Composable
fun PasswordTextField(
    modifier: Modifier = Modifier,
    textFieldValue: String = "",
    singleLine: Boolean = true,
    placeHolderValue: String = "",
    error: String? = null,
    onValueChange: (String) -> Unit = {},
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default
) {
    val outlinedColor = if (error != null) Color.Red else InkGray
    val containerColor = if (error != null) Color.Red.copy(alpha = 0.1f) else Color.White
    var passwordVisibility by rememberSaveable { mutableStateOf(false) }
    val visibilityIconState = if (passwordVisibility) {
        ImageVector.vectorResource(id = R.drawable.open_eye_icon)

    } else {
        ImageVector.vectorResource(id = R.drawable.close_eye_icon)
    }

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
        visualTransformation = if (!passwordVisibility) {
            PasswordVisualTransformation()
        } else {
            VisualTransformation.None
        },
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
                innerTextField()
                if (textFieldValue.isEmpty()) {
                    Text(
                        text = placeHolderValue,
                        style = ParagraphMedium,
                        color = InkGray
                    )
                }
                Icon(
                    imageVector = visibilityIconState,
                    modifier = Modifier
                        .clickable(onClick = {
                            passwordVisibility = !passwordVisibility
                        })
                        .size(18.dp)
                        .align(Alignment.CenterEnd),
                    contentDescription = null,
                )
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeHolderValue = stringResource(id = R.string.email_place_holder),
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.size(16.dp))
        AccentTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
            placeHolderValue = stringResource(id = R.string.name_place_holder),
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.size(16.dp))
        AccentTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeHolderValue = stringResource(id = R.string.search_place_holder),
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.size(16.dp))
        AccentTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
            placeHolderValue = stringResource(id = R.string.phone_place_holder),
            visualTransformation = VisualTransformation.None
        )
        Spacer(modifier = Modifier.size(16.dp))
        PasswordTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Uri),
            placeHolderValue = stringResource(id = R.string.password_place_holder),
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PasswordTextFieldPreview() {
    var password by remember { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(32.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        PasswordTextField(
            textFieldValue = password,
            onValueChange = { password = it },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email),
            placeHolderValue = stringResource(id = R.string.password_place_holder),

            )
    }
}

