package com.samy.cityselector.data.localdata

import android.content.Context
import com.google.gson.Gson
import com.samy.cityselector.R
import com.samy.cityselector.data.entities.CityRaw

class CityRawLocalDataSourceImpl constructor(
    private val context: Context,
    private val gson: Gson
) : CityRawLocalDataSource {

    private val citiesList by lazy {
        context
            .resources
            .openRawResource(R.raw.cities)
            .bufferedReader()
            .use { it.readText() }
            .let { gson.fromJson(it, CityRaw::class.java) }
    }

    override suspend fun getCitiesRaw(): CityRaw = citiesList

}
