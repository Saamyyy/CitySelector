package com.samy.cityselector.domain.usecase

class GetCitesList constructor(
    private val getAllCities: GetAllCities,
    private val searchCities: SearchCities
) {
    suspend fun getCitesList(searchTerm: String = "") =
        if (searchTerm.isEmpty() || searchTerm.isBlank()) {
            getAllCities.getCitesList()
        } else {
            searchCities.applySearchTerm(searchTerm)
        }
}
