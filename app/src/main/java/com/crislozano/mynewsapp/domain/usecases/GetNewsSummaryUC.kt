package com.crislozano.mynewsapp.domain.usecases

import com.crislozano.mynewsapp.data.repository.INewsRepository
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsSummary
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn

/**
 * IGetNewsSummaryUC: Use case that return a flow of list of NewsSummary
 */
interface IGetNewsSummaryUC {
    suspend operator fun invoke(page: Int): Flow<CustomResult<List<NewsSummary>>>
}

class GetNewsSummaryUC(private val repository: INewsRepository): IGetNewsSummaryUC {
    override suspend fun invoke(page: Int): Flow<CustomResult<List<NewsSummary>>> {
        return flow {
            emit(repository.getAllNews(page))
        }.flowOn(Dispatchers.IO)
    }
}