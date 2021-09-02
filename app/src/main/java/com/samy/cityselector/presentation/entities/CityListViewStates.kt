package com.samy.cityselector.presentation.entities

data class CityListViewStates(
    val isProgress: Boolean = false,
    val citiesListViewEntity: CitiesListViewEntity = CitiesListViewEntity(emptyList()),
    val error: Throwable? = null
)

sealed class CityListAction {
    object CityListInit : CityListAction()
    data class Search(val term: String) : CityListAction()
}
