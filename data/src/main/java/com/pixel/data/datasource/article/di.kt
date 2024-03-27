package com.pixel.data.datasource.article

import com.pixel.data.contract.datasource.article.ArticleOnlineDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArticleDataSourceBinder {

    @Binds
    abstract fun bindArticleDataSource(
        articleOnlineDatasourceImpl: ArticleOnlineDatasourceImpl,
    ): ArticleOnlineDatasource
}
