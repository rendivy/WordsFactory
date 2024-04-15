package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import com.yuriyyangel.wordsfactory.di.WidgetFactoryAppDependencies
import com.yuriyyangel.wordsfactory.presentation.widget.ui.WidgetContent
import dagger.hilt.android.EntryPointAccessors


class WordsFactoryWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appContext = context.applicationContext
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            WidgetFactoryAppDependencies::class.java
        )
        val wordsCount = hiltEntryPoint.wordRepository().getWordsCount()

        provideContent {
            WidgetContent(wordsCount)
        }
    }

    private suspend fun updateWidget(context: Context) {
        updateAll(context = context)
    }
}

