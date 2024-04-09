package com.yuriyyangel.dictionary_feature.services

import android.media.AudioAttributes
import android.media.MediaPlayer

class MediaPlayerService(url: String, onCompleteListener: () -> Unit, onStartListener: () -> Unit) {

    val mediaPlayer = MediaPlayer().apply {
        setAudioAttributes(
            AudioAttributes
                .Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC)
                .setUsage(AudioAttributes.USAGE_MEDIA)
                .build()
        )
        setDataSource(url)
        prepare()
        setOnCompletionListener {
            onCompleteListener()
            onDestroy()
        }
        onStartListener()
    }

    private fun onDestroy() {
        mediaPlayer.release()
    }

}