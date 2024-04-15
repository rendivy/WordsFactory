package com.yuriyyangel.wordsfactory.di

import com.keyinc.words.database.WordsDatabase
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class DatabaseHolder @Inject constructor(val database: WordsDatabase)


