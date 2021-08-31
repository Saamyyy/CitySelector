package com.samy.cityselector.data

import com.samy.cityselector.data.localdata.CityRawLocalDataSource
import com.samy.cityselector.data.mapper.CityDomainMapper
import com.samy.cityselector.domain.CityRepository

class CityRepositoryImpl(
    private val localDataSource: CityRawLocalDataSource,
    private val mapper: CityDomainMapper
) : CityRepository {

    override suspend fun getCities() = mapper.apply(localDataSource.getCitiesRaw())
}
