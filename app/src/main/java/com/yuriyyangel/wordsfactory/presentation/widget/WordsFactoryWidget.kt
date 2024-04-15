package com.yuriyyangel.wordsfactory.presentation.widget

import android.content.Context
import androidx.glance.GlanceId
import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.provideContent
import com.yuriyyangel.wordsfactory.presentation.widget.ui.WidgetContent

class WordsFactoryWidget : GlanceAppWidget() {

    override suspend fun provideGlance(context: Context, id: GlanceId) {

        provideContent {
            WidgetContent()
        }
    }


}