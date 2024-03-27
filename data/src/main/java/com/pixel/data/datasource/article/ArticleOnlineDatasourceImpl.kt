package com.pixel.data.datasource.article

import com.pixel.data.api.WebServices
import com.pixel.data.contract.datasource.article.ArticleOnlineDatasource
import com.pixel.domain.model.Article
import javax.inject.Inject

class ArticleOnlineDatasourceImpl @Inject constructor(private val webServices: WebServices) :
    ArticleOnlineDatasource {
    override suspend fun getArticles(sourceID: String): List<Article>? {
        return webServices.getNewsArticle(sourceID).articles?.map { dto ->
            dto?.toArticle()!!
        }
    }
}
