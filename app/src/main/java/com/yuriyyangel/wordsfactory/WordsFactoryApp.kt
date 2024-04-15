package com.yuriyyangel.wordsfactory

import android.app.Application
import com.yuriyyangel.wordsfactory.di.DatabaseHolder
import dagger.hilt.android.HiltAndroidApp
import javax.inject.Inject


@HiltAndroidApp
class WordsFactoryApp : Application() {

    @Inject
    lateinit var databaseHolder: DatabaseHolder

}




