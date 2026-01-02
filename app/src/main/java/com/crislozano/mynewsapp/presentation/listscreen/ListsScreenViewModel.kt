package com.crislozano.mynewsapp.presentation.listscreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.crislozano.mynewsapp.domain.model.CustomResult
import com.crislozano.mynewsapp.domain.usecases.IGetNewsSummaryUC
import com.crislozano.mynewsapp.presentation.model.ListScreenState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

// This is a restriction based on the API. Being a free user, we can only load up to 20 pages.
const val MAX_PAGES = 20

class ListsScreenViewModel(private val getNewsSummaryUC: IGetNewsSummaryUC): ViewModel() {

    private val _state = MutableStateFlow(ListScreenState())
    val state: StateFlow<ListScreenState> = _state.asStateFlow()

    var currentPage: Int = 1

    fun handleIntent(intent: ListScreenIntent) {
        when(intent) {
            ListScreenIntent.RequestNews -> { getNewsSummary() }
            ListScreenIntent.LoadMore -> { getMoreNews() }
        }
    }

    private fun getNewsSummary() {
        currentPage = 1
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            getNewsSummaryUC(currentPage).collect {
                if(it is CustomResult.Success) {
                    if(it.data.isNullOrEmpty()) {
                        _state.value = _state.value.copy(isEmpty = true, isLoading = false, error = null)
                    } else {
                        _state.value = _state.value.copy(news = it.data, isLoading = false, isEmpty = false, error = null, hasMore = currentPage != MAX_PAGES)
                        currentPage++
                    }
                } else {
                    _state.value = _state.value.copy(isLoading = false, isEmpty = false, error = it.errorMessage)
                }
            }

        }
    }

    private fun getMoreNews() {
        if(_state.value.hasMore && !_state.value.isLoadingMore) {
            viewModelScope.launch {
                _state.value = _state.value.copy(isLoadingMore = true)
                getNewsSummaryUC(currentPage).collect {
                    if(it is CustomResult.Success) {
                        if(it.data.isNullOrEmpty()) {
                            _state.value = _state.value.copy(isLoadingMore = false, error = null)
                        } else {
                            val currentNews = _state.value.news + it.data
                            _state.value = _state.value.copy(news = currentNews, isLoadingMore = false, isEmpty = false, error = null, hasMore = currentPage != MAX_PAGES)
                            currentPage++
                        }
                    } else {
                        _state.value = _state.value.copy(isLoadingMore = false, isEmpty = false)
                    }
                }
            }
        }
    }
}


sealed class ListScreenIntent {
    object RequestNews: ListScreenIntent()
    object LoadMore: ListScreenIntent()
}