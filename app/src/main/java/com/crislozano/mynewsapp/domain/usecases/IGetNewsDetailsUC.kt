package com.crislozano.mynewsapp.domain.usecases

import com.crislozano.mynewsapp.data.repository.INewsRepository
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsDetails
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * IGetNewsDetailsUC: Use case that return a flow of NewsDetails
 */
interface IGetNewsDetailsUC {
    suspend operator fun invoke(uuid: String): Flow<CustomResult<NewsDetails>>
}

class GetNewsDetailsUC(private val repository: INewsRepository): IGetNewsDetailsUC {
    override suspend fun invoke(uuid: String): Flow<CustomResult<NewsDetails>> {
        return flow {
            emit(repository.getNewsDetails(uuid))
        }.flowOn(Dispatchers.IO)
    }


}