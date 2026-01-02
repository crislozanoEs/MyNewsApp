package com.crislozano.mynewsapp.data.api

import com.crislozano.mynewsapp.data.model.NewsDetailsDto
import com.crislozano.mynewsapp.data.model.NewsListDto
import com.crislozano.mynewsapp.data.model.NewsSummaryDto
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

/**
 * Interface Api that exposes all the API operations we need for the application
 */
interface Api {

    @GET("news/all?language=en")
    suspend fun getAllNews(@Query("api_token")token: String, @Query("page")page: Int): Response<NewsListDto>


    @GET("news/uuid/{uuid}?language=en")
    suspend fun getNewsDetails(@Path("uuid")uuid: String, @Query("api_token")token: String): Response<NewsDetailsDto>

}