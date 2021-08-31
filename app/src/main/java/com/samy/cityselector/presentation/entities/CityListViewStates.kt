package com.samy.cityselector.presentation.entities

sealed class CityListViewStates {
    object Loading: CityListViewStates()
    object Empty: CityListViewStates()
    data class Error(val message:String): CityListViewStates()
    data class Content(val citiesListViewEntity: CitiesListViewEntity):CityListViewStates()
}
