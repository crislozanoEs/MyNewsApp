package com.crislozano.mynewsapp.data.model

import com.crislozano.mynewsapp.domain.model.NewsSummary
import com.google.gson.annotations.SerializedName

data class NewsSummaryDto(
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
)

fun NewsSummaryDto.toDomain(): NewsSummary {
    return NewsSummary(
        uuid = uuid,
        title = title,
        description = description,
        publishAt = publishAt,
        imageUrl = imageUrl
    )
}