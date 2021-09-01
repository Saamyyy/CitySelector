package com.samy.cityselector.presentation.viewmodel

import com.nhaarman.mockitokotlin2.doAnswer
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.domain.usecase.GetCitesList
import com.samy.cityselector.helper.ViewModelTest
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import com.samy.cityselector.presentation.entities.CityListViewStates
import com.samy.cityselector.presentation.entities.CityViewEntityItem
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test
import kotlin.concurrent.thread


class CitiesListVIewModelTest : ViewModelTest() {
    private val getCitesList: GetCitesList = mock()

    private val viewModel = CitiesListVIewModel(getCitesList)

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
            val expected = CityListViewStates.Content(citiesListViewEntity)
            // act
            viewModel.onViewCreated()
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
            val expected = CityListViewStates.Empty
            // act
            viewModel.onViewCreated()
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
            val expected = CityListViewStates.Error("error")
            // act
            viewModel.onViewCreated()
            // assert
            Assert.assertEquals(expected, viewModel.cityListViewStates.value)
        }
    }

}
