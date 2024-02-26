package com.pixel.newsapp.api.model

import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("category")
    val category: String? = null,
    @SerializedName("country")
    val country: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("id")
    val id: String? = null,
    @SerializedName("language")
    val language: String? = null,
    @SerializedName("name")
    val name: String? = null,
    @SerializedName("url")
    val url: String? = null,
)
