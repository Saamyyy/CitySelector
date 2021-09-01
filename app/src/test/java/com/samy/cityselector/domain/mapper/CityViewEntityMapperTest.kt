package com.samy.cityselector.domain.mapper

import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import com.samy.cityselector.presentation.entities.CitiesListViewEntity
import com.samy.cityselector.presentation.entities.CityViewEntityItem
import org.junit.Assert
import org.junit.Test

class CityViewEntityMapperTest {
    @Test
    fun `given CityDomain should return correct CitiesListViewEntity when calling apply `() {
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
        // act
        val actual = CityViewEntityMapper().apply(cityDomain)
        // assert
        Assert.assertEquals(expected, actual)
    }
}
