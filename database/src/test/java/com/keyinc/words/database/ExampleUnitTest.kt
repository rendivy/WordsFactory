package com.keyinc.words.database

import android.content.Context
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import kotlinx.coroutines.runBlocking
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class WordsDatabaseTest {

    private lateinit var wordsDatabase: WordsDatabase
    private lateinit var db: WordsRoomDatabase

    @Before
    fun createDb() {
        val context = ApplicationProvider.getApplicationContext<Context>()
        db = Room.inMemoryDatabaseBuilder(
            context, WordsRoomDatabase::class.java
        ).build()
        wordsDatabase = WordsDatabase(db)
    }

    @After
    fun closeDb() {
        db.close()
    }

    @Test
    fun testRunInTransaction() = runBlocking {
        var result = 0
        wordsDatabase.runInTransaction {
            result = 1
        }
        assertEquals(1, result)
    }

}