package com.yuriyyangel.wordsfactory.presentation.widget.ui

import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.glance.Button
import androidx.glance.GlanceModifier
import androidx.glance.GlanceTheme
import androidx.glance.action.actionStartActivity
import androidx.glance.appwidget.cornerRadius
import androidx.glance.background
import androidx.glance.layout.Alignment
import androidx.glance.layout.Column
import androidx.glance.layout.Row
import androidx.glance.layout.height
import androidx.glance.layout.padding
import androidx.glance.layout.width
import androidx.glance.text.Text
import com.yuriyyangel.wordsfactory.MainActivity

@Composable
fun WidgetContent() {
    Column(
        modifier = GlanceModifier.cornerRadius(21.dp).width(329.dp).height(155.dp)
            .background(GlanceTheme.colors.background),
        verticalAlignment = Alignment.Top,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(text = "Where to?", modifier = GlanceModifier.padding(12.dp))
        Row(horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                text = "Home",
                onClick = actionStartActivity<MainActivity>()
            )
            Button(
                text = "Work",
                onClick = actionStartActivity<MainActivity>()
            )
        }
    }
}
