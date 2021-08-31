package com.samy.cityselector.data.entities

import com.google.gson.annotations.SerializedName

class CityRaw(
    @SerializedName("cities")
    val cities: List<CityRawItem>

)

data class CityRawItem(
    @SerializedName("country")
    val country: String,
    @SerializedName("name")
    val name: String,
    @SerializedName("coord")
    val location: LocationRaw
)

data class LocationRaw(
    @SerializedName("lat")
    val lat: String,
    @SerializedName("lon")
    val lon: String
)
