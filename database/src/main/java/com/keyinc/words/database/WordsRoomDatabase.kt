package com.keyinc.words.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.keyinc.words.database.dao.DefinitionDao
import com.keyinc.words.database.dao.MeaningDao
import com.keyinc.words.database.dao.PhoneticDao
import com.keyinc.words.database.dao.WordDao
import com.keyinc.words.database.entity.DefinitionDBO
import com.keyinc.words.database.entity.MeaningDBO
import com.keyinc.words.database.entity.PhoneticDBO
import com.keyinc.words.database.entity.WordDBO


class WordsDatabase internal constructor(private val database: WordsRoomDatabase) {

    fun runInTransaction(block: () -> Unit) {
        database.runInTransaction {
            block()
        }
    }

    val wordDao: WordDao
        get() = database.wordDao()

    val meaningDao: MeaningDao
        get() = database.meaningDao()

    val definitionDao: DefinitionDao
        get() = database.definitionDao()

    val phoneticDao: PhoneticDao
        get() = database.phoneticDao()

}

@Database(
    entities = [WordDBO::class, MeaningDBO::class, DefinitionDBO::class, PhoneticDBO::class],
    version = 1,
    exportSchema = false
)
internal abstract class WordsRoomDatabase : RoomDatabase() {

    abstract fun wordDao(): WordDao

    abstract fun meaningDao(): MeaningDao

    abstract fun definitionDao(): DefinitionDao

    abstract fun phoneticDao(): PhoneticDao
}

fun WordsDatabase(applicationContext: Context): WordsDatabase {
    val wordsRoomDatabase = Room.databaseBuilder(
        applicationContext,
        WordsRoomDatabase::class.java,
        "words_database"
    ).build()
    return WordsDatabase(wordsRoomDatabase)
}