package com.example.repfluentv2.util

import okhttp3.Interceptor
import okhttp3.Response

class AuthIntercepter: Interceptor {

    private val sessionManager = SessionManage()

    override fun intercept(chain: Interceptor.Chain): Response {
        val requestBuilder = chain.request().newBuilder()

        // If token has been saved, add it to the request
        sessionManager.fetchAuthToken()?.let {
            requestBuilder.addHeader("Authorization", "Bearer $it")
        }

        return chain.proceed(requestBuilder.build())
    }


}