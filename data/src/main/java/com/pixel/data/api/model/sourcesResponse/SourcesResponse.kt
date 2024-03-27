package com.pixel.data.api.model.sourcesResponse

import com.google.gson.annotations.SerializedName
import com.pixel.data.api.model.SourceDto

data class SourcesResponse(
    @SerializedName("sources")
    val sources: List<SourceDto?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
)
