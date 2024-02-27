package com.pixel.newsapp.api.model.articleResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.pixel.newsapp.api.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class Article(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: Source? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
) : Parcelable
