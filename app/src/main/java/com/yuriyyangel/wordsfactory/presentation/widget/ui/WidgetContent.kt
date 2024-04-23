package com.yuriyyangel.wordsfactory.presentation.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import ru.yangel.dictionary_data.observer.WordsObservable


@Composable
fun WidgetContent(wordsObservable: WordsObservable<Int>) {
    val wordCount by wordsObservable.state.collectAsState()
    Column(
        modifier = GlanceModifier.cornerRadius(21.dp).width(329.dp).height(155.dp)
            .background(GlanceTheme.colors.background),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Words in dictionary: $wordCount")
    }
}


