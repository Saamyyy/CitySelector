package com.samy.cityselector.domain.usecase

import com.samy.cityselector.domain.entities.CityDomain
import com.samy.cityselector.domain.entities.CityDomainItem
import org.junit.Assert
import org.junit.Test

class ApplySearchTermTest {

    private val alabamaCityDomainItem = CityDomainItem(
        cityNameCountry = "Alabama, US",
        lat = "lat",
        lon = "lon"
    )

    private val albuquerqueCityDomainItem = CityDomainItem(
        cityNameCountry = "Albuquerque, US",
        lat = "lat",
        lon = "lon"
    )
    private val sydneyCityDomainItem = CityDomainItem(
        cityNameCountry = "Sydney, AU",
        lat = "lat",
        lon = "lon"
    )
    private val cityDomain =
        CityDomain(listOf(alabamaCityDomainItem, albuquerqueCityDomainItem, sydneyCityDomainItem))

    @Test
    fun `calling applySearchTerm should return correct filtered list`() {
        // arrange
        val searchTerm = "Al"
        val expected = CityDomain(listOf(alabamaCityDomainItem, albuquerqueCityDomainItem))

        // act
        val actual = ApplySearchTerm().applySearchTerm(cityDomain, searchTerm)

        // assert
        Assert.assertEquals(expected, actual)
    }
    @Test
    fun `calling applySearchTerm should be case insensitive and return correct filtered list `() {
        // arrange
        val searchTerm = "al"
        val expected = CityDomain(listOf(alabamaCityDomainItem, albuquerqueCityDomainItem))

        // act
        val actual = ApplySearchTerm().applySearchTerm(cityDomain, searchTerm)

        // assert
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun `calling applySearchTerm should return empty list if no items found`() {
        // arrange
        val searchTerm = "aaaa"
        val expected = CityDomain(listOf())

        // act
        val actual = ApplySearchTerm().applySearchTerm(cityDomain, searchTerm)

        // assert
        Assert.assertEquals(expected, actual)
    }
}

