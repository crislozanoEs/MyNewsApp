package com.crislozano.mynewsapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.model.NewsDetails
import com.crislozano.mynewsapp.domain.usecases.IGetNewsDetailsUC
import com.crislozano.mynewsapp.presentation.detailscreen.DetailsScreenViewModel
import com.crislozano.mynewsapp.presentation.model.DetailsScreenState
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
class NewsDetailsViewModelTest {

    // Rule to execute tasks synchronously
    @get:Rule
    val instantExecutorRule = InstantTaskExecutorRule()

    // Mocks and test subject
    private lateinit var getNewsDetailsUC: IGetNewsDetailsUC
    private lateinit var viewModel: DetailsScreenViewModel


    // Test dispatcher for coroutines
    private val testDispatcher = StandardTestDispatcher()

    @Before
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        getNewsDetailsUC = mockk()
        viewModel = DetailsScreenViewModel(getNewsDetailsUC)
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun `getNewsDetails WHEN use case returns success THEN state is updated to success`() =
        runTest {
            // Given
            val uuid = "test-uuid"
            val newsDetails =
                NewsDetails(
                    uuid = "test-uuid",
                    title = "Test Title",
                    description = "Test description",
                    publishAt = "2023-01-01T00:00:00Z",
                    imageUrl = "",
                    snippet = "Test snippet",
                    source = ""
                )
            val successResult = CustomResult.Success(newsDetails)
            val expectedState = DetailsScreenState(news = newsDetails, isLoading = false, error = null)

            // Mock the use case to return a success result
            coEvery { getNewsDetailsUC(uuid) } returns flowOf(successResult)

            // When
            viewModel.getNewsDetails(uuid)

            // Then
            // Ensure coroutines complete
            advanceUntilIdle()

            val currentState = viewModel.state.value
            assertEquals(expectedState, currentState)
        }

    @Test
    fun `getNewsDetails WHEN use case returns error THEN state is updated to error`() = runTest {
        // Given
        val uuid = "test-uuid"
        val errorMessage = "Failed to fetch details"
        val errorResult: CustomResult<NewsDetails> = CustomResult.Error(errorMessage)
        val expectedState = DetailsScreenState(isLoading = false, error = errorMessage)

        // Mock the use case to return an error result
        coEvery { getNewsDetailsUC(uuid) } returns flowOf(errorResult)

        // When
        viewModel.getNewsDetails(uuid)

        // Then
        advanceUntilIdle()

        val currentState = viewModel.state.value
        assertEquals(expectedState, currentState)
    }
}