package com.crislozano.mynewsapp.presentation.model

import com.crislozano.mynewsapp.domain.model.NewsSummary
import java.util.Collections.emptyList

data class ListScreenState(
    val news: List<NewsSummary> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMore: Boolean = false,
    val error: String? = null,
    val isEmpty: Boolean = false,
)