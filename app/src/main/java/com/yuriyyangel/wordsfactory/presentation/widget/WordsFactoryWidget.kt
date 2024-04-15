package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import ru.yangel.widget_feature.ui.WidgetContent
import ru.yangel.widget_feature.worker.GetWordInDictionaryWorker

class WordsFactoryWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {
        val workerRequest = OneTimeWorkRequestBuilder<GetWordInDictionaryWorker>().build()
        val workManager = WorkManager.getInstance(context)
        workManager.enqueue(workerRequest)
        val wordCount =
            workManager.getWorkInfoById(workerRequest.id).get().outputData.getInt("wordsCount", 0)
        provideContent {
            WidgetContent(wordCount = wordCount)
        }
    }

}