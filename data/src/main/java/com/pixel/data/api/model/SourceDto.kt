package com.pixel.data.api.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import com.pixel.domain.model.Source
import kotlinx.parcelize.Parcelize

@Parcelize
data class SourceDto(
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
) : Parcelable {
    fun toSource(): Source {
        return Source(
            category,
            country,
            description,
            id,
            language,
            name,
            url,
        )
    }
}
