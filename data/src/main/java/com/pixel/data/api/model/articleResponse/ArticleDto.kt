package com.pixel.data.api.model.articleResponse

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.pixel.data.api.model.SourceDto
import com.pixel.domain.model.Article
import kotlinx.parcelize.Parcelize

@Parcelize
data class ArticleDto(
    @SerializedName("author")
    val author: String? = null,
    @SerializedName("content")
    val content: String? = null,
    @SerializedName("description")
    val description: String? = null,
    @SerializedName("publishedAt")
    val publishedAt: String? = null,
    @SerializedName("source")
    val source: SourceDto? = null,
    @SerializedName("title")
    val title: String? = null,
    @SerializedName("url")
    val url: String? = null,
    @SerializedName("urlToImage")
    val urlToImage: String? = null,
) : Parcelable {
    fun toArticle(): Article {
        return Article(
            author,
            content,
            description,
            publishedAt,
            source?.toSource(),
            title,
            url,
            urlToImage,
        )
    }
}
