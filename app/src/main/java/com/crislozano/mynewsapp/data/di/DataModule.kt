package com.crislozano.mynewsapp.data.di


import com.crislozano.mynewsapp.data.api.Api
import com.crislozano.mynewsapp.data.repository.INewsRepository
import com.crislozano.mynewsapp.data.repository.NewsRepository
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

private const val HOST = "https://api.thenewsapi.com/v1/"

val dataModule = module {

    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(60, TimeUnit.SECONDS)
            .readTimeout(60, TimeUnit.SECONDS)
            .build()
    }

    single {
        Retrofit.Builder()
            .baseUrl(HOST)
            .addConverterFactory(GsonConverterFactory.create())
            .build().create(Api::class.java)
    }


    single<INewsRepository> { NewsRepository(get()) }

}