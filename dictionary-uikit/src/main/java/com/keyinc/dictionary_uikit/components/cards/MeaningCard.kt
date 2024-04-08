package com.keyinc.dictionary_uikit.components.cards

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.keyinc.dictionary_uikit.R
import com.keyinc.dictionary_uikit.theme.InkGray
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingSmall
import com.keyinc.dictionary_uikit.theme.ParagraphMedium
import com.keyinc.dictionary_uikit.theme.SecondaryColor


@Composable
@Preview(showBackground = true)
fun MeaningCard(
    modifier: Modifier = Modifier,
    meaningText: String? = stringResource(id = R.string.meaning_place_holder),
    exampleText: String? = stringResource(id = R.string.example_place_holder)
) {

    val exampleTextAnnotated = buildAnnotatedString {
        withStyle(style = SpanStyle(color = SecondaryColor)) {
            append(stringResource(id = R.string.example))
        }
        append(exampleText)
    }

    OutlinedCard(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(10.dp))
            .padding(
                top = PaddingSmall,
                start = PaddingMedium,
                end = PaddingMedium,
                bottom = PaddingMedium
            ),
        colors = CardDefaults.cardColors(
            containerColor = Color.White,
        ),
        border = BorderStroke(1.dp, InkGray),
    )
    {
        Text(
            modifier = Modifier.padding(
                top = PaddingMedium,
                start = PaddingMedium,
                end = PaddingMedium,
                bottom = PaddingMedium
            ),
            style = ParagraphMedium,
            color = Color.Black,
            text = meaningText ?: "",
        )
        if (exampleText != null)
            Text(
                text = exampleTextAnnotated,
                modifier = Modifier.padding(
                    start = PaddingMedium,
                    end = PaddingMedium,
                    bottom = PaddingMedium
                ),
                style = ParagraphMedium,
                color = Color.Black,
            )
    }
}


@Composable
@Preview(showBackground = true, device = "id:pixel_6_pro")
fun MeaningCardPreview() {
    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
    ) {
        repeat(10) {
            item {
                MeaningCard(
                    exampleText = null
                )
            }
        }
    }
}





