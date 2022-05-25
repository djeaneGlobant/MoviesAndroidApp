package com.example.networkmodule.interceptor

import com.example.networkmodule.BuildConfig
import okhttp3.Interceptor
import okhttp3.Response
import javax.inject.Inject

class AuthorizationInterceptor @Inject constructor() : Interceptor {

    companion object {
        private const val AUTH_NAME= "Authorization"
    }

    override fun intercept(chain: Interceptor.Chain): Response {
        val request = chain.request().newBuilder()
            .addHeader(AUTH_NAME, BuildConfig.AUTH_TOKEN)
            .build()

        return chain.proceed(request)
    }

}