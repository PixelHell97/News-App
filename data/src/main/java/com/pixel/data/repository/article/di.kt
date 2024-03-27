package com.pixel.data.repository.article

import com.pixel.domain.contract.repository.articles.ArticleRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class ArticleRepoBinder {

    @Binds
    abstract fun bindArticleRepo(
        articleRepositoryImpl: ArticleRepositoryImpl,
    ): ArticleRepository
}
