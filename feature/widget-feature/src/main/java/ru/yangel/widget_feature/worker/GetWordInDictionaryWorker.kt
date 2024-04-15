package ru.yangel.widget_feature.worker

import android.content.Context
import androidx.hilt.work.HiltWorker
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import dagger.assisted.Assisted
import dagger.assisted.AssistedInject
import ru.yangel.dictionary_data.repository.WordRepository

@HiltWorker
class GetWordInDictionaryWorker @AssistedInject constructor(
    @Assisted context: Context,
    @Assisted workerParams: WorkerParameters,
    private val wordsRepository: WordRepository
) : CoroutineWorker(context, workerParams) {

    override suspend fun doWork(): Result {
        return try {
            val wordsCount = getWordsCount()
            val outputData = workDataOf("wordsCount" to wordsCount)
            Result.success(outputData)
        }
        catch (e: Exception) {
            Result.retry()
        }
    }


    private suspend fun getWordsCount(): Int {
        return wordsRepository.getWordsCount()
    }
}