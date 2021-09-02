package com.samy.cityselector.domain.usecase

import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.verify
import kotlinx.coroutines.runBlocking
import org.junit.Test


class GetCitesListTest {
    private val getAllCities: GetAllCities = mock()
    private val searchCities: SearchCities = mock()

    private val getCitesList = GetCitesList(getAllCities, searchCities)

    @Test
    fun `calling getCitesList with empty string should getAllCities`() {
        runBlocking {
            // act
            getCitesList.getCitesList("")
            // assert
            verify(getAllCities).getCitesList()
        }
    }

    @Test
    fun `calling getCitesList with blank string should getAllCities`() {
        runBlocking {
            // act
            getCitesList.getCitesList("         ")
            // assert
            verify(getAllCities).getCitesList()
        }
    }

    @Test
    fun `calling getCitesList with valid string should applySearchTerm`() {
        runBlocking {
            val searchTerm = "search"
            // act
            getCitesList.getCitesList(searchTerm)
            // assert
            verify(searchCities).applySearchTerm(searchTerm)
        }
    }
}
