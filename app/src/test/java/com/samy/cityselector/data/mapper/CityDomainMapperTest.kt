package com.samy.cityselector.data.mapper

import com.samy.cityselector.data.entities.CityRaw
import com.samy.cityselector.data.entities.CityRawItem
import com.samy.cityselector.data.entities.LocationRaw
import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import org.junit.Assert
import org.junit.Test

class CityDomainMapperTest {

    @Test
    fun `given cityRawItem should return correct CityDomain when calling apply `() {
        // arrange
        val cityRawItem = CityRawItem(
            country = "EG",
            name = "cairo",
            location = LocationRaw("lat", "lon")
        )
        val cityRaw = CityRaw(listOf(cityRawItem))

        val cityDomainItem = CityDomainItem(
            cityNameCountry = "cairo, EG",
            lat = "lat",
            lon = "lon"
        )
        val expected = CityDomain(listOf(cityDomainItem))
        // act
        val actual = CityDomainMapper().apply(cityRaw)
        // assert
        Assert.assertEquals(expected, actual)
    }
}
