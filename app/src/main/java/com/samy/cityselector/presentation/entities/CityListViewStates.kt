package com.samy.cityselector.presentation.entities

data class CityListViewStates(
    val isProgress: Boolean = false,
    val citiesListViewEntity: CitiesListViewEntity = CitiesListViewEntity(emptyList()),
    val error: Throwable? = null
)

sealed class CityListAction {
    object InitCityList : CityListAction()
    data class SearchCityList(val term: String) : CityListAction()
}
