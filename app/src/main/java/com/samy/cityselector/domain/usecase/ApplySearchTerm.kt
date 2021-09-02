package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.entities.CityDomain

class ApplySearchTerm {
    fun applySearchTerm(allCities: CityDomain, searchTerm: String): CityDomain {
        val filteredCities =
            allCities.cities
                .filter { it.cityNameCountry.lowercase().startsWith(searchTerm.lowercase()) }
        return CityDomain(filteredCities)
    }
}
