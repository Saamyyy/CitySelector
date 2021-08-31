package com.samy.cityselector.data.localdata

import com.samy.cityselector.data.entities.CityRaw

interface CityRawLocalDataSource {
    suspend fun getCitiesRaw(): CityRaw
}
