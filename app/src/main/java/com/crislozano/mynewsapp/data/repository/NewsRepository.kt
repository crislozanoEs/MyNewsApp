package com.crislozano.mynewsapp.data.repository

import com.crislozano.mynewsapp.BuildConfig
import com.crislozano.mynewsapp.data.api.Api
import com.crislozano.mynewsapp.data.model.toDomain
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsDetails
import com.crislozano.mynewsapp.domain.model.NewsSummary

/**
 * INewsRepository: Interface that represent the repository of news.
 * Operations: Get a list of news Summary, and get the details of a specific news.
 */
interface INewsRepository {

    // Return the list of News Summaries, based on the page sent
    suspend fun getAllNews(page: Int): CustomResult<List<NewsSummary>>

    // Return Details of the selected news based on the uuid
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