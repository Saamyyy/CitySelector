package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.mapper.CityViewEntityMapper

class GetCitesList constructor(
    private val cityRepository: CityRepository,
    private val mapper: CityViewEntityMapper
) {
    suspend fun getCitesList() = mapper.apply(cityRepository.getCities())
}
