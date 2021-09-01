package com.samy.cityselector.domain.usecase

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.domain.CityRepository
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import com.samy.cityselector.domain.mapper.CityViewEntityMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetCitesListTest {
    private val cityRepository: CityRepository = mock()
    private val mapper: CityViewEntityMapper = mock()

    private val useCase = GetCitesList(cityRepository, mapper)

    @Test
    fun `calling getCitesList should call apply from mapper`() {
        runBlocking {
            // arrange
            val cityDomainItem = CityDomainItem(
                cityNameCountry = "cairo, EG",
                lat = "lat",
                lon = "lon"
            )
            val cityDomain = CityDomain(listOf(cityDomainItem))
            whenever(cityRepository.getCities()).thenReturn(cityDomain)
            // act
            useCase.getCitesList()
            // assert
            verify(mapper).apply(any())
        }
    }

    @Test
    fun `calling getCitesList should call getCities from cityRepository`() {
        runBlocking {
            // act
            useCase.getCitesList()
            // assert
            verify(cityRepository).getCities()
        }
    }

}
