package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.yuriyyangel.wordsfactory.presentation.widget.ui.WidgetContent
import ru.yangel.dictionary_data.observer.WordsObservable

// https://developer.android.com/develop/ui/compose/glance/glance-app-widget
//TODO: Прокинуть стейт для обновления
class WordsFactoryWidget(private val wordsUpdateObservable: WordsObservable<Int>) :
    GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        provideContent {
            WidgetContent(wordsUpdateObservable)
        }
    }

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
    }


}

