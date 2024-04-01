package com.keyinc.dictionary_uikit.components.snackbar

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Snackbar
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import com.keyinc.dictionary_uikit.theme.ErrorColor
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.ParagraphMedium


@Composable
fun SnackBar(message: String) {
    Snackbar(
        containerColor = ErrorColor,
        modifier = Modifier
            .padding(PaddingMedium)
            .clip(shape = RoundedCornerShape(PaddingMedium))
    ) {
        Row {
            Text(
                text = message,
                modifier = Modifier.fillMaxWidth(),
                style = ParagraphMedium,
                textAlign = TextAlign.Center,
                color = Color.White
            )
        }
    }
}