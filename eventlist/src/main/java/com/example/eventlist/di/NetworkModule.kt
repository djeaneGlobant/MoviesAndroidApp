package com.example.eventlist.di

import com.example.eventlist.data.network.EventApi
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Named

@Module
class NetworkModule {
    @Provides
    fun provideMovieApi(@Named("baseUrl") url: String): EventApi {
        val interceptor = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        val client = OkHttpClient.Builder().addInterceptor(interceptor)
            .addInterceptor(headersInterceptor)
            .build()
        val retrofit = Retrofit.Builder()
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(url)
            .build()
        return retrofit.create()
    }

    private val headersInterceptor = Interceptor { chain ->
        val requestBuilder = chain.request().newBuilder()
        val headers = getHeaders()
        headers.forEach {
            requestBuilder.addHeader(it.key, it.value ?: "")
        }
        chain.proceed(requestBuilder.build())
    }

    private fun getHeaders(): HashMap<String, String?> {
        return HashMap<String, String?>().apply {
            put("Authorization", "Bearer X8h3u75CZBbtIB4u9HwxLl4PxtJOdTZNS1k3npuLPYGDRKu1orVH_EyuD-D_SDRD4sjO21QzfS0JR_7YHWBd-qN9i8DaoShiQu07DkisSDqZIrJbCSZmOZj4btGDYnYx")
        }
    }

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = "https://api.yelp.com/"
}