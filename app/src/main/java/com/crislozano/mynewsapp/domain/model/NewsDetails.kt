package com.crislozano.mynewsapp.domain.model

data class NewsDetails(
    val uuid: String,
    val title: String,
    val description: String,
    val publishAt: String,
    val imageUrl: String,
    val snippet: String,
    val source: String,
)
