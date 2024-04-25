package com.yuriyyangel.wordsfactory.presentation.widget.ui

import android.content.Context
import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Paint
import android.text.TextPaint
import androidx.annotation.FontRes
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.res.ResourcesCompat
import androidx.core.util.TypedValueCompat.spToPx
import androidx.glance.GlanceModifier
import androidx.glance.Image
import androidx.glance.ImageProvider
import androidx.glance.LocalContext
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Box
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.Spacer
import androidx.glance.layout.fillMaxSize
import androidx.glance.layout.fillMaxWidth
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import com.keyinc.dictionary_uikit.theme.InkDarkGray
import com.keyinc.dictionary_uikit.theme.InkLightGray
import com.keyinc.dictionary_uikit.theme.PaddingLarge
import com.keyinc.dictionary_uikit.theme.PaddingMedium
import com.keyinc.dictionary_uikit.theme.PaddingTiny
import com.keyinc.dictionary_uikit.theme.PrimaryColor
import ru.yangel.dictionary_data.observer.WordsObservable


@Composable
fun WidgetContent(wordsObservable: WordsObservable<Int>) {
    val wordCount by wordsObservable.state.collectAsState()
    val gradient = Brush.linearGradient(
        colors = listOf(
            PrimaryColor,
            Color(0xFF65AAEA)
        )
    )
    Box(
        modifier = GlanceModifier
            .cornerRadius(20.dp)
            .width(329.dp)
            .height(155.dp)
    ) {
        Column(
            modifier = GlanceModifier
                .cornerRadius(20.dp)
                .fillMaxSize()
                .background(Color.White),
            verticalAlignment = Alignment.CenterVertically,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(start = PaddingMedium, top = PaddingLarge),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlanceText(
                    text = "My Dictionary",
                    font = com.keyinc.dictionary_uikit.R.font.rubik_medium,
                    fontSize = 20.sp,
                )
                Spacer(modifier = GlanceModifier.defaultWeight())
                GlanceText(
                    text = "$wordCount Words",
                    font = com.keyinc.dictionary_uikit.R.font.rubick_regular,
                    fontSize = 14.sp,
                    color = InkDarkGray,
                    modifier = GlanceModifier.padding(end = PaddingMedium),
                )
            }
            Row(
                modifier = GlanceModifier
                    .fillMaxWidth()
                    .padding(start = PaddingMedium, top = PaddingMedium),
                verticalAlignment = Alignment.CenterVertically,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                GlanceText(
                    text = "I already remembered",
                    font = com.keyinc.dictionary_uikit.R.font.rubik_medium,
                    fontSize = 20.sp,
                )
                Spacer(modifier = GlanceModifier.defaultWeight())
                GlanceText(
                    text = "$wordCount Words",
                    font = com.keyinc.dictionary_uikit.R.font.rubick_regular,
                    fontSize = 14.sp,
                    color = InkDarkGray,
                    modifier = GlanceModifier.padding(end = PaddingMedium),
                )
            }
        }
    }
    Box(
        modifier = GlanceModifier
            .width(329.dp)
            .height(50.dp)
            .background(PrimaryColor)
    ) {
        Box {
            GlanceText(
                text = "WordsFactory",
                font = com.keyinc.dictionary_uikit.R.font.rubik_medium,
                fontSize = 30.sp,
                color = Color.White,
                modifier = GlanceModifier.padding(top = PaddingTiny, end = PaddingTiny),
            )
        }
        GlanceText(
            text = "WordsFactory",
            modifier = GlanceModifier.padding(top = PaddingTiny, end = PaddingTiny),
            fontSize = 30.sp,
            font = com.keyinc.dictionary_uikit.R.font.rubik_medium,
            color = InkLightGray
        )

    }
}


@Composable
fun GlanceText(
    text: String,
    @FontRes font: Int,
    fontSize: TextUnit,
    modifier: GlanceModifier = GlanceModifier,
    color: Color = Color.Black,
    letterSpacing: TextUnit = (-0.05).sp
) {
    Image(
        modifier = modifier,
        provider = ImageProvider(
            LocalContext.current.textAsBitmap(
                text = text,
                fontSize = fontSize,
                color = color,
                font = font,
                letterSpacing = letterSpacing.value
            )
        ),
        contentDescription = null,
    )
}

fun Context.textAsBitmap(
    text: String,
    fontSize: TextUnit,
    color: Color = Color.Black,
    letterSpacing: Float = 0.01f,
    font: Int
): Bitmap {
    val paint = TextPaint(Paint.ANTI_ALIAS_FLAG)
    paint.textSize = spToPx(fontSize.value, this.resources.displayMetrics)
    paint.color = color.toArgb()
    paint.letterSpacing = letterSpacing
    paint.typeface = ResourcesCompat.getFont(this, font)

    val baseline = -paint.ascent()
    val width = (paint.measureText(text)).toInt()
    val height = (baseline + paint.descent()).toInt()
    val image = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888)
    val canvas = Canvas(image)
    canvas.drawText(text, 0f, baseline, paint)
    return image
}


