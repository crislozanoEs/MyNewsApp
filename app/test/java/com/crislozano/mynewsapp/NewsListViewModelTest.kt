package com.crislozano.mynewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsSummary
import com.crislozano.mynewsapp.domain.usecases.IGetNewsSummaryUC
import com.crislozano.mynewsapp.presentation.listscreen.ListsScreenViewModel
import com.crislozano.mynewsapp.presentation.model.ListScreenState
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runTest
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class NewsListViewModelTest {

    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var getNewsSummaryUC: IGetNewsSummaryUC
    private lateinit var viewModel: ListsScreenViewModel

    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsSummaryUC = mockk()
        viewModel = ListsScreenViewModel(getNewsSummaryUC)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNewsSummary WHEN use case returns success THEN state is updated to success`() =
        runTest {
            // Given
            val newsList = listOf(NewsSummary(uuid = "1", title = "Test Title", imageUrl = "", description = "", publishAt = ""))
            val successResult = CustomResult.Success(newsList)
            val expectedState = ListScreenState(news = newsList, isLoading = false, isEmpty = false, error = null, hasMore = true)

            coEvery { getNewsSummaryUC(1) } returns flowOf(successResult)

            // When
            viewModel.handleIntent(com.crislozano.mynewsapp.presentation.listscreen.ListScreenIntent.RequestNews)

            // Then
            advanceUntilIdle()

            val currentState = viewModel.state.value
            assertEquals(expectedState, currentState)
        }

    @Test
    fun `getNewsSummary WHEN use case returns error THEN state is updated to error`() = runTest {
        // Given
        val errorMessage = "Failed to fetch news"
        val errorResult: CustomResult<List<NewsSummary>> = CustomResult.Error(errorMessage)
        val expectedState = ListScreenState(isLoading = false, isEmpty = false, error = errorMessage)

        coEvery { getNewsSummaryUC(1) } returns flowOf(errorResult)

        // When
        viewModel.handleIntent(com.crislozano.mynewsapp.presentation.listscreen.ListScreenIntent.RequestNews)

        // Then
        advanceUntilIdle()

        val currentState = viewModel.state.value
        assertEquals(expectedState, currentState)
    }
}