package com.pixel.data.contract.datasource.sources

import com.pixel.domain.model.Source

interface SourcesOnlineDatasource {
    suspend fun getSources(category: String): List<Source>?
}
