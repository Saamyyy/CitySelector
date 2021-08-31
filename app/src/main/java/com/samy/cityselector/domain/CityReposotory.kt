package com.samy.cityselector.domain

import com.samy.cityselector.domain.entities.CityDomain

interface CityRepository {
    suspend fun getCities() : CityDomain
}
