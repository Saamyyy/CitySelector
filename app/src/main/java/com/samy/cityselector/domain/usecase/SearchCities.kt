package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.mapper.CityViewEntityMapper
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import java.util.*

class SearchCities(
    private val cityRepository: CityRepository,
    private val mapper: CityViewEntityMapper,
    private val applySearchTerm: ApplySearchTerm,
    private val applySortRules: ApplySortRules
) {
    suspend fun applySearchTerm(searchTerm: String): CitiesListViewEntity {
        val allCities = cityRepository.getCities()
        return mapper.apply(applySortRules(getFilteredCities(allCities, searchTerm)))
    }

    private fun getFilteredCities(allCities: CityDomain, searchTerm: String) =
        applySearchTerm.applySearchTerm(allCities, searchTerm)

    private fun applySortRules(allCities: CityDomain) =
        applySortRules.applySortRules(allCities)
}
