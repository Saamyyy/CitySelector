package com.samy.cityselector.domain.entities

data class CityDomain(
    val cities: List<CityDomainItem>
)

data class CityDomainItem(
    val cityNameCountry: String,
    val lat: String,
    val lon: String
)
