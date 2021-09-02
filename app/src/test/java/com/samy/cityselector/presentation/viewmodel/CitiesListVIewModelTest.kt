package com.samy.cityselector.presentation.viewmodel

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.domain.usecase.SearchCities
import com.samy.cityselector.helper.ViewModelTest
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.entities.CityViewEntityItem
import com.samy.cityselector.presentation.entities.NoResultFound
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class CitiesListVIewModelTest : ViewModelTest() {
    private val getCitesList: GetCitesList = mock()
    private val searchCities: SearchCities = mock()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

    @Test
    fun `calling onViewCreated with success from useCase should emits Content`() {

        runBlocking {
            // arrange
            val cityViewEntityItem = CityViewEntityItem(
                title = "cairo, EG",
                supTitle = "lat, lon",
                lat = "lat",
                lon = "lon"
            )
            val citiesListViewEntity = CitiesListViewEntity(listOf(cityViewEntityItem))
            whenever(getCitesList.getCitesList()).thenReturn(citiesListViewEntity)
            val expected = CityListViewStates(citiesListViewEntity = citiesListViewEntity)
            // act
            val viewModel = CitiesListVIewModel(getCitesList,searchCities,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }


    @Test
    fun `calling onViewCreated with empty list  from useCase should emits empty`() {
        runBlocking {
            // arrange
            val citiesListViewEntity = CitiesListViewEntity(listOf())
            whenever(getCitesList.getCitesList()).thenReturn(citiesListViewEntity)
            val expected = CityListViewStates(error = NoResultFound)
            // act
            val viewModel = CitiesListVIewModel(getCitesList,searchCities,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

    @Test
    fun `calling onViewCreated with error from useCase should emits error`() {
        runBlocking {
            // arrange
            val throwable = Throwable(message = "error")
            whenever(getCitesList.getCitesList()) doAnswer {
                throw throwable
            }
            val expected = CityListViewStates(error = throwable)
            // act
            val viewModel = CitiesListVIewModel(getCitesList,searchCities,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }
}
