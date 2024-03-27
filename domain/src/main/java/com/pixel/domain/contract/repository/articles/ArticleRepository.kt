package com.pixel.domain.contract.repository.articles

import com.pixel.domain.model.Article

interface ArticleRepository {
    suspend fun getArticle(sourceID: String): List<Article>?
}
