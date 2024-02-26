package com.pixel.newsapp.api.model.articleResponse

import com.google.gson.annotations.SerializedName

data class ArticlesResponse(
    @SerializedName("articles")
    val articles: List<Article?>? = null,
    @SerializedName("status")
    val status: String? = null,
    @SerializedName("totalResults")
    val totalResults: Int? = null,
    @SerializedName("code")
    val code: String? = null,
    @SerializedName("message")
    val message: String? = null,
)
