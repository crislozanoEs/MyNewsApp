package com.crislozano.mynewsapp.domain.model

data class NewsSummary(
    val uuid: String,
    val title: String,
    val description: String,
    val publishAt: String,
    val imageUrl: String,
)