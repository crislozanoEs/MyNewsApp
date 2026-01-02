package com.crislozano.mynewsapp.presentation.model

import com.crislozano.mynewsapp.domain.model.NewsDetails

data class DetailsScreenState(
    val news: NewsDetails? = null,
    val isLoading: Boolean = false,
    val error: String? = null,
)