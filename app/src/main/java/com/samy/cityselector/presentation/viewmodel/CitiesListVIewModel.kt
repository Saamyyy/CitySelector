package com.samy.cityselector.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.domain.usecase.SearchCities
import com.samy.cityselector.presentation.entities.CityListAction
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.entities.NoResultFound
import kotlinx.coroutines.*

class CitiesListVIewModel(
    private val getCitesList: GetCitesList,
    private val searchCities: SearchCities,
    private val dispatcher: CoroutineDispatcher = Dispatchers.IO
) : ViewModel() {
    val cityListViewStates: MutableLiveData<CityListViewStates> =
        MutableLiveData<CityListViewStates>()
    val cityListAction: MutableLiveData<CityListAction> = MutableLiveData<CityListAction>()

    private var searchJob: Job? = null
    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            cityListViewStates.postValue(CityListViewStates(error = throwable))
        }

    init {
        cityListAction.postValue(CityListAction.CityListInit)
        cityListAction.observeForever { model(it) }
    }

    private fun model(it: CityListAction?) {
        searchJob?.cancelChildren()
        searchJob?.cancel()
        searchJob = viewModelScope.launch (exceptionHandler + dispatcher) {
            when (it) {
                is CityListAction.CityListInit -> getCitiesList("")
                is CityListAction.Search -> {
                    delay(200)
                    getCitiesList(it.term)
                }
            }
        }
    }

    private suspend fun getCitiesList(searchTerm: String) {
        cityListViewStates.postValue(CityListViewStates(isProgress = true))
        val citesList =
            if (searchTerm.trim().isEmpty())
                getCitesList.getCitesList()
            else
                searchCities.applySearchTerm(searchTerm)
        if (citesList.cities.isEmpty()) {
            cityListViewStates.postValue(CityListViewStates(error = NoResultFound))
        } else {
            cityListViewStates.postValue(CityListViewStates(citiesListViewEntity = citesList))
        }
    }
}
