package com.pixel.data.contract.datasource.article

import com.pixel.domain.model.Article

interface ArticleOnlineDatasource {
    suspend fun getArticles(sourceID: String): List<Article>?
}
