package com.samy.cityselector.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import com.samy.cityselector.domain.mapper.CityViewEntityMapper
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import com.samy.cityselector.presentation.entities.CityViewEntityItem
import kotlinx.coroutines.runBlocking
import org.junit.Assert
import org.junit.Test

class SearchCitiesTest {
    private val cityRepository: CityRepository = mock()
    private val mapper: CityViewEntityMapper = mock()
    private val applySearchTerm: ApplySearchTerm = mock()
    private val applySortRules: ApplySortRules = mock()
    private val searchCities = SearchCities(
        cityRepository,
        mapper,
        applySearchTerm,
        applySortRules
    )

    @Test
    fun `calling applySearchTerm will call getCities from cityRepository`() {
        runBlocking {
            //arrange
            val searchTerm = "searchTerm"
            // act
            searchCities.applySearchTerm(searchTerm)
            //assert
            verify(cityRepository).getCities()
        }
    }

    @Test
    fun `calling applySearchTerm will call getFilteredCities from applySearchTerm with cities from repo`() {
        runBlocking {
            //arrange
            val searchTerm = "searchTerm"
            // arrange
            val cityDomainItem = CityDomainItem(
                cityNameCountry = "cairo, EG",
                lat = "lat",
                lon = "lon"
            )
            val cityDomain = CityDomain(listOf(cityDomainItem))
            whenever(cityRepository.getCities()).thenReturn(cityDomain)
            // act
            searchCities.applySearchTerm(searchTerm)
            //assert
            verify(applySearchTerm).applySearchTerm(cityDomain, searchTerm)
        }
    }

    @Test
    fun `calling applySearchTerm will call applySortRules from applySortRules with cities from applySearchTerm`() {
        runBlocking {
            //arrange
            val searchTerm = "searchTerm"
            // arrange
            val cityDomainItem = CityDomainItem(
                cityNameCountry = "cairo, EG",
                lat = "lat",
                lon = "lon"
            )
            val cityDomain = CityDomain(listOf(cityDomainItem))
            whenever(cityRepository.getCities()).thenReturn(cityDomain)
            whenever(applySearchTerm.applySearchTerm(cityDomain,searchTerm)).thenReturn(cityDomain)
            // act
            searchCities.applySearchTerm(searchTerm)
            //assert
            verify(applySortRules).applySortRules(cityDomain)
        }
    }


    @Test
    fun `calling applySearchTerm will call apply from mapper with cities from applySortRules`() {
        runBlocking {
            //arrange
            val searchTerm = "searchTerm"
            // arrange
            val cityDomainItem = CityDomainItem(
                cityNameCountry = "cairo, EG",
                lat = "lat",
                lon = "lon"
            )
            val cityDomain = CityDomain(listOf(cityDomainItem))
            whenever(cityRepository.getCities()).thenReturn(cityDomain)
            whenever(applySearchTerm.applySearchTerm(cityDomain,searchTerm)).thenReturn(cityDomain)
            whenever(applySortRules.applySortRules(cityDomain)).thenReturn(cityDomain)

            // act
            searchCities.applySearchTerm(searchTerm)
            //assert
            verify(mapper).apply(cityDomain)
        }
    }

    @Test
    fun `calling applySearchTerm will return CitiesListViewEntity from mapper`() {
        runBlocking {
            //arrange
            val searchTerm = "searchTerm"
            // arrange
            val cityDomainItem = CityDomainItem(
                cityNameCountry = "cairo, EG",
                lat = "lat",
                lon = "lon"
            )
            val cityDomain = CityDomain(listOf(cityDomainItem))
            val cityViewEntityItem= CityViewEntityItem(
                title = "cairo, EG",
                supTitle= "lat, lon",
                lat = "lat",
                lon= "lon"
            )

            val expected= CitiesListViewEntity(listOf(cityViewEntityItem))
            whenever(cityRepository.getCities()).thenReturn(cityDomain)
            whenever(applySearchTerm.applySearchTerm(cityDomain,searchTerm)).thenReturn(cityDomain)
            whenever(applySortRules.applySortRules(cityDomain)).thenReturn(cityDomain)
            whenever(mapper.apply(cityDomain)).thenReturn(expected)

            // act
            val actual= searchCities.applySearchTerm(searchTerm)
            //assert

            Assert.assertEquals(expected, actual)
        }
    }
}
