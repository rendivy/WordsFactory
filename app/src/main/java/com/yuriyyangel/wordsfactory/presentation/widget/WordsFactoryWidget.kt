package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.compose.runtime.LaunchedEffect
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.glance.appwidget.updateAll
import com.yuriyyangel.wordsfactory.di.WidgetFactoryAppDependencies
import com.yuriyyangel.wordsfactory.presentation.widget.ui.WidgetContent
import dagger.hilt.android.EntryPointAccessors
import kotlinx.coroutines.flow.consumeAsFlow

// https://developer.android.com/develop/ui/compose/glance/glance-app-widget
//TODO: Прокинуть стейт для обновления
class WordsFactoryWidget : GlanceAppWidget() {


    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val appContext = context.applicationContext
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            appContext,
            WidgetFactoryAppDependencies::class.java
        )
        val wordsCount = hiltEntryPoint.wordRepository().getWordsCount()
        if (wordsCount == 0) {
            provideContent {
                WidgetContent(wordsCount)
            }
        } else {
            val wordsObservable = hiltEntryPoint.wordsObservable().buffer.consumeAsFlow()
            wordsObservable.collect {
                when (it) {
                    true -> updateWidget(context)
                    false -> {}
                }
            }
            provideContent {
                LaunchedEffect(wordsCount) {
                    wordsObservable.collect {
                        when (it) {
                            true -> updateWidget(context)
                            false -> {}
                        }
                    }
                }
                WidgetContent(wordsCount)
            }
        }

    }

    private suspend fun updateWidget(context: Context) {
        updateAll(context = context)
    }

    override suspend fun onDelete(context: Context, glanceId: GlanceId) {
        val hiltEntryPoint = EntryPointAccessors.fromApplication(
            context,
            WidgetFactoryAppDependencies::class.java
        )
        hiltEntryPoint.wordsObservable().buffer.close()
        super.onDelete(context, glanceId)
    }
}

