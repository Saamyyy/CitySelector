package com.samy.cityselector.presentation.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.presentation.entities.CityListViewStates
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.launch

class CitiesListVIewModel(
    private val getCitesList: GetCitesList
) : ViewModel() {
    val cityListViewStates = MutableLiveData<CityListViewStates>()

    private val exceptionHandler: CoroutineExceptionHandler =
        CoroutineExceptionHandler { _, throwable ->
            cityListViewStates.postValue(CityListViewStates.Error(throwable.message ?: ""))
        }

    fun onViewCreated() {
        viewModelScope.launch(exceptionHandler) {
            cityListViewStates.postValue(CityListViewStates.Loading)
            val citesList = getCitesList.getCitesList()
            if (citesList.cities.isEmpty()) {
                cityListViewStates.postValue(CityListViewStates.Empty)
            } else {
                cityListViewStates.postValue(CityListViewStates.Content(citesList))
            }
        }
    }
}
