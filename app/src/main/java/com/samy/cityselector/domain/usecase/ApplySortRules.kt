package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.entities.CityDomain

class ApplySortRules {
    fun applySortRules(cities: CityDomain): CityDomain {
        val filteredCities = cities.cities
            .sortedBy { it.cityNameCountry }
        return CityDomain(filteredCities)
    }
}
