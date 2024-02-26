package com.pixel.newsapp.api

import com.pixel.newsapp.Constants
import com.pixel.newsapp.api.model.articleResponse.ArticlesResponse
import com.pixel.newsapp.api.model.sourcesResponse.SourcesResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    fun getNewsSources(
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = Constants.apiKey,
    ): Call<SourcesResponse>

    @GET("v2/top-headlines")
    fun getNewsArticle(
        @Query("sources") sources: String?,
        @Query("apiKey") apiKey: String = Constants.apiKey,
    ): Call<ArticlesResponse>
}
