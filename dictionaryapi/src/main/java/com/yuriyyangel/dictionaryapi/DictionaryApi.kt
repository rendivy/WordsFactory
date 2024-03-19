package com.yuriyyangel.dictionaryapi

import com.skydoves.retrofit.adapters.result.ResultCallAdapterFactory
import com.yuriyyangel.dictionaryapi.models.WordResponseItem
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path



/**
 * Details [here](https://github.com/meetDeveloper/freeDictionaryAPI)
 */

interface DictionaryApi {

    @GET("{word}")
    suspend fun getWordDefinition(@Path("word") word: String): Result<List<WordResponseItem>>
}

fun DictionaryApi(baserUrl: String, okHttpClient: OkHttpClient? = null): DictionaryApi {
    val retrofit = retrofit(baserUrl, okHttpClient)
    return retrofit.create(DictionaryApi::class.java)
}

fun retrofit(baserUrl: String, okHttpClient: OkHttpClient? = null): Retrofit {

    return Retrofit.Builder()
        .baseUrl(baserUrl)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(ResultCallAdapterFactory.create())
        .run { if (okHttpClient != null) client(okHttpClient) else this }
        .build()
}
