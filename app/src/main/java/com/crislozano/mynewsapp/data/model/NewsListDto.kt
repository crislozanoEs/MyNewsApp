package com.crislozano.mynewsapp.data.model

import com.google.gson.annotations.SerializedName

data class NewsListDto(
    @SerializedName("meta")
    val meta: MetaDto,
    @SerializedName("data")
    val data: List<NewsSummaryDto>
)

data class MetaDto(
    @SerializedName("found")
    val found: Int,
    @SerializedName("returned")
    val returned: Int,
    @SerializedName("limit")
    val limit: Int,
    @SerializedName("page")
    val page: Int

)