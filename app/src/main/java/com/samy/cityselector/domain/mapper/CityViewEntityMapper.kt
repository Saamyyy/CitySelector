package com.samy.cityselector.domain.mapper

import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import com.samy.cityselector.presentation.CitiesListViewEntity
import com.samy.cityselector.presentation.CityViewEntityItem

class CityViewEntityMapper {
    fun apply(cityDomain: CityDomain): CitiesListViewEntity {
        val cityViewEntityItems = mutableListOf<CityViewEntityItem>()
        cityDomain.cities
            .map { cityViewEntityItems.add(getCityViewEntityItem(it)) }
        return CitiesListViewEntity(
            cities = cityViewEntityItems
        )
    }

    private fun getCityViewEntityItem(it: CityDomainItem) =
        CityViewEntityItem(
            title = it.cityNameCountry,
            supTitle = listOf(it.lat, it.lon).joinToString(),
            lat = it.lat,
            lon = it.lon
        )
}
