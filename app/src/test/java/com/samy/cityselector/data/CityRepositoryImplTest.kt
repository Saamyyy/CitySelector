package com.samy.cityselector.data

import com.nhaarman.mockitokotlin2.any
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.samy.cityselector.data.entities.CityRaw
import com.samy.cityselector.data.entities.CityRawItem
import com.samy.cityselector.data.entities.LocationRaw
import com.samy.cityselector.data.localdata.CityRawLocalDataSource
import com.samy.cityselector.data.mapper.CityDomainMapper
import kotlinx.coroutines.runBlocking
import org.junit.Test

class CityRepositoryImplTest {
    private val localDataSource: CityRawLocalDataSource = mock()
    private val mapper: CityDomainMapper = mock()

    private val repo = CityRepositoryImpl(localDataSource, mapper)

    @Test
    fun `should call getCitiesRaw from localDataSource when calling getCities from repo `() {
        runBlocking {
            // act
            repo.getCities()
            //verify
            verify(localDataSource).getCitiesRaw()
        }
    }

    @Test
    fun `should call apply from mapper when calling  getCities from repo `() {
        runBlocking {
            // arrange
            val cityRawItem = CityRawItem(
                country = "EG",
                name = "cairo",
                location = LocationRaw("lat", "lon")
            )
            val cityRaw = CityRaw(listOf(cityRawItem))
            whenever(localDataSource.getCitiesRaw()).thenReturn(cityRaw)
            // act
            repo.getCities()
            //verify
            verify(mapper).apply(any())
        }
    }
}

