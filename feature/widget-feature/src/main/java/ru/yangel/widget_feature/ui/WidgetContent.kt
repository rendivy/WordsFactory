package ru.yangel.widget_feature.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.height
import androidx.glance.layout.width
import androidx.glance.text.Text


@Composable
fun WidgetContent(wordCount: Int) {
    Column(
        modifier = GlanceModifier.cornerRadius(21.dp).width(329.dp).height(155.dp)
            .background(GlanceTheme.colors.background),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Words in dictionary: $wordCount")
    }
}


