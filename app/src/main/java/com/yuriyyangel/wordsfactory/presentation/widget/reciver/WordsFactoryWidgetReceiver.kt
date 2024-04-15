package com.yuriyyangel.wordsfactory.presentation.widget.reciver

import androidx.glance.appwidget.GlanceAppWidget
import androidx.glance.appwidget.GlanceAppWidgetReceiver
import com.yuriyyangel.wordsfactory.presentation.widget.WordsFactoryWidget

class WordsFactoryWidgetReceiver(override val glanceAppWidget: GlanceAppWidget = WordsFactoryWidget()) :
    GlanceAppWidgetReceiver()