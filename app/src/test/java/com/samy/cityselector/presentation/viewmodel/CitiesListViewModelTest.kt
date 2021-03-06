package com.samy.cityselector.presentation.viewmodel

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.helper.ViewModelTest
import com.samy.cityselector.presentation.entities.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test


class CitiesListViewModelTest : ViewModelTest() {
    private val getCitesList: GetCitesList = mock()
    private val dispatcher: CoroutineDispatcher = Dispatchers.Main

    @Test
    fun `create view model with success from useCase should emits Content`() {

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
            val viewModel = CitiesListViewModel(getCitesList,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }


    @Test
    fun `create view model with empty list  from useCase should emits empty`() {
        runBlocking {
            // arrange
            val citiesListViewEntity = CitiesListViewEntity(listOf())
            whenever(getCitesList.getCitesList()).thenReturn(citiesListViewEntity)
            val expected = CityListViewStates(error = NoResultFound)
            // act
            val viewModel = CitiesListViewModel(getCitesList,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

    @Test
    fun `create view model with error from useCase should emits error`() {
        runBlocking {
            // arrange
            val throwable = Throwable(message = "error")
            whenever(getCitesList.getCitesList()) doAnswer {
                throw throwable
            }
            val expected = CityListViewStates(error = throwable)
            // act
            val viewModel = CitiesListViewModel(getCitesList,dispatcher)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

    @Test
    fun `pushing SearchCityList to viewModel with success from useCase should emits Content`() {

        runBlocking {
            // arrange
            val searchTerm= "newText"
            val cityViewEntityItem = CityViewEntityItem(
                title = "cairo, EG",
                supTitle = "lat, lon",
                lat = "lat",
                lon = "lon"
            )
            val citiesListViewEntity = CitiesListViewEntity(listOf(cityViewEntityItem))
            whenever(getCitesList.getCitesList(searchTerm)).thenReturn(citiesListViewEntity)
            val expected = CityListViewStates(citiesListViewEntity = citiesListViewEntity)
            val viewModel = CitiesListViewModel(getCitesList,dispatcher,0)
            // act
            viewModel.cityListAction.value=CityListAction.SearchCityList(searchTerm)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

    @Test
    fun `pushing SearchCityList to viewModel with empty list  from useCase should emits empty`() {
        runBlocking {
            // arrange
            val searchTerm= "newText"
            val citiesListViewEntity = CitiesListViewEntity(listOf())
            whenever(getCitesList.getCitesList(searchTerm)).thenReturn(citiesListViewEntity)
            val expected = CityListViewStates(error = NoResultFound)
            val viewModel = CitiesListViewModel(getCitesList,dispatcher,0)
            // act
            viewModel.cityListAction.value=CityListAction.SearchCityList(searchTerm)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

    @Test
    fun `pushing SearchCityList to viewModel with error from useCase should emits error`() {
        runBlocking {
            // arrange
            val searchTerm= "newText"
            val throwable = Throwable(message = "error")
            whenever(getCitesList.getCitesList(searchTerm)) doAnswer {
                throw throwable
            }
            val expected = CityListViewStates(error = throwable)
            val viewModel = CitiesListViewModel(getCitesList,dispatcher,0)
            // act
            viewModel.cityListAction.value=CityListAction.SearchCityList(searchTerm)
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }
    @Test
    fun `currentSearchTerm from viewModel should holds the search term value `() {
        runBlocking {
            // arrange
            val searchTerm= "newText"
            val throwable = Throwable(message = "error")
            whenever(getCitesList.getCitesList(searchTerm)) doAnswer {
                throw throwable
            }
            val viewModel = CitiesListViewModel(getCitesList,dispatcher,0)
            viewModel.cityListAction.value=CityListAction.SearchCityList(searchTerm)
            // act
           val actual=  viewModel.currentSearchTerm
            // assert
            Assert.assertEquals(searchTerm,actual)
        }
    }
}
