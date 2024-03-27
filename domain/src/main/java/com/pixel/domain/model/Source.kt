package com.pixel.domain.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Source(
    val category: String? = null,
    val country: String? = null,
    val description: String? = null,
    val id: String? = null,
    val language: String? = null,
    val name: String? = null,
    val url: String? = null,
) : Parcelable
