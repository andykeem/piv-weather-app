package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.ForecastResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

interface GetForecastDataUseCase {
    suspend operator fun invoke(query: String, days: Int): NetworkResponse<ForecastResponse, Unit>
}

class DefaultGetForecastDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi,
    private val ioDispatcher: CoroutineDispatcher
) : GetForecastDataUseCase {
    override suspend fun invoke(query: String, days: Int): NetworkResponse<ForecastResponse, Unit> {
        return withContext(ioDispatcher) {
            weatherApi.getForecast(query, days)
        }
    }
}