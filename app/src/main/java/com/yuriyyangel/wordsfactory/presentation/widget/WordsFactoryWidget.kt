package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.yuriyyangel.wordsfactory.di.WidgetFactoryAppDependencies
import com.yuriyyangel.wordsfactory.presentation.widget.ui.WidgetContent
import dagger.hilt.android.EntryPointAccessors

// https://developer.android.com/develop/ui/compose/glance/glance-app-widget
//TODO: Прокинуть стейт для обновления
class WordsFactoryWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appContext = context.applicationContext
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            WidgetFactoryAppDependencies::class.java
        )
        val wordsUpdateObservable = hiltEntryPoint.wordsObservable()

        provideContent {
            WidgetContent(wordsUpdateObservable)
        }

    }


    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        super.onDelete(context, glanceId)
    }
}

