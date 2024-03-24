package com.pixel.newsapp.data.api

import com.pixel.newsapp.Constants
import com.pixel.newsapp.data.api.model.articleResponse.ArticlesResponse
import com.pixel.newsapp.data.api.model.sourcesResponse.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = Constants.apiKey,
    ): SourcesResponse

    @GET("v2/top-headlines")
    suspend fun getNewsArticle(
        @Query("sources") sources: String?,
        @Query("apiKey") apiKey: String = Constants.apiKey,
    ): ArticlesResponse
}
