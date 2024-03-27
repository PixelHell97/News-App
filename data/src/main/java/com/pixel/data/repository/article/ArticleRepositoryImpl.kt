package com.pixel.data.repository.article

import com.pixel.data.contract.datasource.article.ArticleOnlineDatasource
import com.pixel.domain.contract.repository.articles.ArticleRepository
import com.pixel.domain.model.Article
import javax.inject.Inject

class ArticleRepositoryImpl @Inject constructor(
    private val articleOnlineDatasource: ArticleOnlineDatasource,
) : ArticleRepository {
    override suspend fun getArticle(sourceID: String): List<Article>? {
        return articleOnlineDatasource.getArticles(sourceID)
    }
}
