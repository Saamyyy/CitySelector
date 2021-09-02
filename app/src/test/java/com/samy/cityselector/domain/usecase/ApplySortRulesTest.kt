package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import org.junit.Assert
import org.junit.Test

class ApplySortRulesTest {
    private val kCityDomainItem = CityDomainItem(
        cityNameCountry = "k, US",
        lat = "lat",
        lon = "lon"
    )

    private val iCityDomainItem = CityDomainItem(
        cityNameCountry = "Albuquerque, US",
        lat = "lat",
        lon = "lon"
    )
    private val jCityDomainItem = CityDomainItem(
        cityNameCountry = "Sydney, AU",
        lat = "lat",
        lon = "lon"
    )
    private val cityDomain =
        CityDomain(listOf(kCityDomainItem, iCityDomainItem, jCityDomainItem))

    @Test
    fun `calling applySortRules should return sorted list by cityNameCountry`() {
        // arrange
        val expected =
            CityDomain(listOf(iCityDomainItem, jCityDomainItem, kCityDomainItem))
        //act
        val actual = ApplySortRules().applySortRules(cityDomain)
        // assert
        Assert.assertEquals(expected, actual)
    }

}
