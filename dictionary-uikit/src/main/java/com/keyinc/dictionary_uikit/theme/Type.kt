package com.keyinc.dictionary_uikit.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.I
import androidx.compose.ui.text.Paragraph
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.keyinc.dictionary_uikit.R


val Typography = Typography(
    bodyLarge = TextStyle(
        fontFamily = FontFamily.Default,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    )
)

val Heading1 = TextStyle(
    fontFamily = FontFamily(
        Font(R.font.rubik_medium)
    ),
    fontWeight = FontWeight.Medium,
    fontSize = LargeFontSize
)

val ButtonMedium = TextStyle(
    fontFamily = FontFamily(
        Font(R.font.rubik_medium)
    ),
    fontWeight = FontWeight.Medium,
    fontSize = 14.sp
)

val ParagraphMedium =  TextStyle(
    fontFamily = FontFamily(
        Font(R.font.rubick_regular)
    ),
    color = InkDarkGray,
    fontWeight = FontWeight.Normal,
    fontSize = SmallFontSize
)

val ButtonSmall = TextStyle(
    fontFamily = FontFamily(
        Font(R.font.rubik_medium)
    ),
    fontWeight = FontWeight.Medium,
    fontSize = SmallFontSize
)