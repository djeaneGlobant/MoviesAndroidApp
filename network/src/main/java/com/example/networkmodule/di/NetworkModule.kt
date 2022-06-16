package com.example.networkmodule.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.networkmodule.BuildConfig
import com.example.networkmodule.api.BusinessApi
import com.example.networkmodule.api.BusinessApiImpl
import com.example.networkmodule.interceptor.AuthorizationInterceptor
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
class NetworkModule {

    @Singleton
    @Provides
    fun provideGson(): Gson =
        GsonBuilder().create()


    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder {
        val builder = OkHttpClient.Builder()
            .readTimeout(15, TimeUnit.SECONDS)
            .connectTimeout(15, TimeUnit.SECONDS)
            val httpLoggingInterceptor = HttpLoggingInterceptor()
            httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            builder.addInterceptor(httpLoggingInterceptor)
        return builder
    }

    @Singleton
    @Provides
    fun provideApolloClient(
        okHttpClientBuilder: OkHttpClient.Builder,
        authorizationInterceptor: AuthorizationInterceptor
    ): ApolloClient {
        return ApolloClient.builder()
            .serverUrl(BuildConfig.SERVER_URL)
            .okHttpClient(
                okHttpClientBuilder
                    .addInterceptor(authorizationInterceptor)
                    .build()
            ).build()
    }

    @Provides
    fun provideMovieApiImpl(
        apolloClient: ApolloClient,
        gson: Gson
    ): BusinessApi =
        BusinessApiImpl(apolloClient, gson)

}