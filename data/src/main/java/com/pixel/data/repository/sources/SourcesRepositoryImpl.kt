package com.pixel.data.repository.sources

import com.pixel.data.contract.datasource.sources.SourcesOnlineDatasource
import com.pixel.domain.contract.repository.sources.SourcesRepository
import com.pixel.domain.model.Source
import javax.inject.Inject

class SourcesRepositoryImpl @Inject constructor(
    private val sourcesOnlineDatasource: SourcesOnlineDatasource,
) : SourcesRepository {
    override suspend fun getSources(category: String): List<Source>? {
        return sourcesOnlineDatasource.getSources(category)
    }
}
