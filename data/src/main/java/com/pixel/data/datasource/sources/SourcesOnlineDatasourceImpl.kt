package com.pixel.data.datasource.sources

import com.pixel.data.api.WebServices
import com.pixel.data.contract.datasource.sources.SourcesOnlineDatasource
import com.pixel.domain.model.Source
import javax.inject.Inject

class SourcesOnlineDatasourceImpl @Inject constructor(private val webServices: WebServices) :
    SourcesOnlineDatasource {
    override suspend fun getSources(category: String): List<Source>? {
        return webServices.getNewsSources(category).sources?.map { dto ->
            dto?.toSource()!!
        }
    }
}
