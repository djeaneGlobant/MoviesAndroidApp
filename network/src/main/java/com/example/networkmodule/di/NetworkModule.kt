package com.example.networkmodule.di

import com.example.networkmodule.api.MoviesApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.create

@Module
class NetworkModule {

    @Provides
    fun provideNetworkApi(url: String): MoviesApi {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
        val retrofit = Retrofit.Builder().client(client).baseUrl(url).build()
        return retrofit.create()
    }

    @Provides
    fun provideBaseUrl() = "https://api.themoviedb.org/3/movie/"
}