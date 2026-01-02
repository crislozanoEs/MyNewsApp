package com.crislozano.mynewsapp.data.repository

import android.util.Log
import com.crislozano.mynewsapp.BuildConfig
import com.crislozano.mynewsapp.data.api.Api
import com.crislozano.mynewsapp.data.model.toDomain
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsDetails
import com.crislozano.mynewsapp.domain.model.NewsSummary

interface INewsRepository {

    suspend fun getAllNews(page: Int): CustomResult<List<NewsSummary>>
    suspend fun getNewsDetails(uuid: String): CustomResult<NewsDetails>
}

class NewsRepository(private val api: Api): INewsRepository {
    override suspend fun getAllNews(page: Int): CustomResult<List<NewsSummary>> {
        try {
            val token = BuildConfig.API_KEY
            val response = api.getAllNews(token, page)
            if (response.isSuccessful) {
                val body = response.body()?.data?.map {
                    it.toDomain()
                } ?: emptyList()
                return CustomResult.Success(body)
            } else {
                return CustomResult.Error(response.errorBody()?.string() ?: "Error getting news")
            }
        } catch (e: Exception) {
            return CustomResult.Error(e.message ?: "Error getting news")
        }
    }

    override suspend fun getNewsDetails(uuid: String): CustomResult<NewsDetails> {
        try {
            val token = BuildConfig.API_KEY
            val response = api.getNewsDetails(uuid, token)
            if (response.isSuccessful) {
                response.body()?.let{
                    return CustomResult.Success(it.toDomain())
                }
                return CustomResult.Success(NewsDetails(
                    "", "", "", "", "", "", ""
                ))
            } else {
                return CustomResult.Error(response.errorBody()?.string() ?: "Error getting news details")
            }
        } catch (e: Exception) {
            return CustomResult.Error(e.message ?: "Error getting news details")
        }
    }


}