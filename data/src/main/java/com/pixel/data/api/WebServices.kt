package com.pixel.data.api

import com.pixel.data.api.model.articleResponse.ArticlesResponse
import com.pixel.data.api.model.sourcesResponse.SourcesResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface WebServices {
    companion object {
        const val API_KEY = "db86dae49e4741b1a09a9fc452960b56"
    }
    
    @GET("v2/top-headlines/sources")
    suspend fun getNewsSources(
        @Query("category") category: String?,
        @Query("apiKey") apiKey: String = API_KEY,
    ): SourcesResponse

    @GET("v2/top-headlines")
    suspend fun getNewsArticle(
        @Query("sources") sources: String?,
        @Query("apiKey") apiKey: String = API_KEY,
    ): ArticlesResponse
}
