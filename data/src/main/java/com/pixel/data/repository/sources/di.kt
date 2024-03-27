package com.pixel.data.repository.sources

import com.pixel.domain.contract.repository.sources.SourcesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourceRepoBinder {

    @Binds
    abstract fun bindSourceRepo(
        sourcesRepositoryImpl: SourcesRepositoryImpl,
    ): SourcesRepository
}
