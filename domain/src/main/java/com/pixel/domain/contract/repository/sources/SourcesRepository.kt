package com.pixel.domain.contract.repository.sources

import com.pixel.domain.model.Source

interface SourcesRepository {
    suspend fun getSources(category: String): List<Source>?
}
