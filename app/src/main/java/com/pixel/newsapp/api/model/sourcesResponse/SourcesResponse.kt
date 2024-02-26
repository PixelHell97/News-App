package com.pixel.newsapp.api.model.sourcesResponse

import com.google.gson.annotations.SerializedName
import com.pixel.newsapp.api.model.Source

data class SourcesResponse(
    @SerializedName("sources")
    val sources: List<Source?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
)
