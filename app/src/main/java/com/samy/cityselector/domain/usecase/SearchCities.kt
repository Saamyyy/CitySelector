package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.mapper.CityViewEntityMapper
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import java.util.*

class SearchCities(
    private val cityRepository: CityRepository,
    private val mapper: CityViewEntityMapper
) {
    suspend fun applySearchTerm(searchTerm: String): CitiesListViewEntity {
        val sortedCities = cityRepository.getCities()
            .cities
            .filter { it.cityNameCountry.lowercase().startsWith(searchTerm.lowercase()) }
            .sortedBy { it.cityNameCountry }
        return mapper.apply(CityDomain(sortedCities))
    }
}
