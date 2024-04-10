package com.yuriyyangel.dictionaryapi.interceptor

import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import ru.yangel.core.customexception.WordNotFound

class NotFoundInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        val response = chain.proceed(request)
        when (response.code) {
            404 -> {
                throw WordNotFound()
            }
        }
        return response
    }
}