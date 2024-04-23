package com.yuriyyangel.wordsfactory.presentation.widget.reciver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.yuriyyangel.wordsfactory.presentation.widget.WordsFactoryWidget
import dagger.hilt.android.AndroidEntryPoint
import ru.yangel.dictionary_data.observer.WordsObservable
import javax.inject.Inject


@AndroidEntryPoint
class WordsFactoryWidgetReceiver : GlanceAppWidgetReceiver() {

    @Inject
    lateinit var wordsUpdateObservable: WordsObservable<Int>

    override val glanceAppWidget: GlanceAppWidget
        get() = WordsFactoryWidget(wordsUpdateObservable)
}