package com.samy.cityselector.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.presentation.entities.CityListAction
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.entities.NoResultFound
import kotlinx.coroutines.*

class CitiesListVIewModel(
    private val getCitesList: GetCitesList,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO,
    private val cancelDelayPeriod: Long = 200L
) : ViewModel() {
    val cityListViewStates: MutableLiveData<CityListViewStates> =
        MutableLiveData<CityListViewStates>()
    val cityListAction: MutableLiveData<CityListAction> = MutableLiveData<CityListAction>()
    var currentSearchTerm: String = ""

    private var searchJob: Job? = null
    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            cityListViewStates.postValue(CityListViewStates(error = throwable))
        }

    init {
        cityListAction.postValue(CityListAction.InitCityList)
        cityListAction.observeForever { handleActions(it) }
    }

    private fun handleActions(it: CityListAction?) {
        cancelActiveJobs()
        searchJob = viewModelScope.launch(exceptionHandler + dispatcher) {
            when (it) {
                is CityListAction.InitCityList -> getCitiesList("")
                is CityListAction.SearchCityList -> handleSearchAction(it)
            }
        }
    }

    private fun cancelActiveJobs() {
        searchJob?.cancelChildren()
        searchJob?.cancel()
    }

    private suspend fun handleSearchAction(searchCityListAction: CityListAction.SearchCityList) {
        delay(cancelDelayPeriod)
        currentSearchTerm = searchCityListAction.term
        getCitiesList(searchCityListAction.term)
    }

    private suspend fun getCitiesList(searchTerm: String) {
        cityListViewStates.postValue(CityListViewStates(isProgress = true))
        val citesList = getCitesList.getCitesList(searchTerm)
        if (citesList.cities.isEmpty()) {
            cityListViewStates.postValue(CityListViewStates(error = NoResultFound))
        } else {
            cityListViewStates.postValue(CityListViewStates(citiesListViewEntity = citesList))
        }
    }
}
