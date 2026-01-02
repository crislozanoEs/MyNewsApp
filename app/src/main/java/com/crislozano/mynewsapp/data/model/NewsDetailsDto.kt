package com.crislozano.mynewsapp.data.model

import com.crislozano.mynewsapp.domain.model.NewsDetails
import com.google.gson.annotations.SerializedName

data class NewsDetailsDto(
    @SerializedName("uuid")
    val uuid: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("published_at")
    val publishAt: String,
    @SerializedName("image_url")
    val imageUrl: String,
    @SerializedName("snippet")
    val snippet: String,
    @SerializedName("source")
    val source: String,
)

fun NewsDetailsDto.toDomain(): NewsDetails {
    return NewsDetails(
        uuid = uuid,
        title = title,
        description = description,
        publishAt = publishAt,
        imageUrl = imageUrl,
        snippet = snippet,
        source = source
    )
}
