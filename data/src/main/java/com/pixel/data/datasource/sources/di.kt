package com.pixel.data.datasource.sources

import com.pixel.data.contract.datasource.sources.SourcesOnlineDatasource
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SourceDataSourceBinder {

    @Binds
    abstract fun bindSourceDataSource(
        sourcesOnlineDatasourceImpl: SourcesOnlineDatasourceImpl,
    ): SourcesOnlineDatasource
}
