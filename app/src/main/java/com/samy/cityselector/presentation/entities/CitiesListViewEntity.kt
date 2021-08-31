package com.samy.cityselector.presentation.entities

data class CitiesListViewEntity(
    val cities: List<CityViewEntityItem>
)

data class CityViewEntityItem(
    val title: String,
    val supTitle: String,
    val lat: String,
    val lon: String
)
