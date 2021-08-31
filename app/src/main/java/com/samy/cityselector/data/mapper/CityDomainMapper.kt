package com.samy.cityselector.data.mapper

import com.samy.cityselector.data.entities.CityRaw
import com.samy.cityselector.data.entities.CityRawItem
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem

class CityDomainMapper {
    fun apply(cityRaw: CityRaw): CityDomain {
        val cityDomainList = mutableListOf<CityDomainItem>()
         cityRaw.cities
            .map { cityDomainList.add(getCityDomainItem(it)) }
        return CityDomain(cityDomainList)
    }

    private fun getCityDomainItem(it: CityRawItem) =
        CityDomainItem(
            cityNameCountry = listOf(it.name, it.country).joinToString(),
            lat = it.location.lat,
            lon = it.location.lon
        )
}
