package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.AutoCompleteResponse
import com.png.interview.weather.api.model.AutoCompleteResponseItem
import javax.inject.Inject

interface GetLocationDataUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<List<AutoCompleteResponseItem>, Unit>
}

class DefaultGetLocationDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi
) : GetLocationDataUseCase {
    override suspend fun invoke(query: String): NetworkResponse<List<AutoCompleteResponseItem>, Unit> {
        return weatherApi.getAutocompleteResults(query)
    }
}