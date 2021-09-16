package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.api.WeatherApi
import com.png.interview.weather.api.model.CurrentWeatherResponse
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import timber.log.Timber
import javax.inject.Inject

interface GetCurrentWeatherDataUseCase {
    suspend operator fun invoke(query: String): NetworkResponse<CurrentWeatherResponse, Unit>
}

private const val TAG = "GetCurrentWeatherDataUs"

class DefaultGetCurrentWeatherDataUseCase @Inject constructor(
    private val weatherApi: WeatherApi,
    private val ioDispatcher: CoroutineDispatcher
) : GetCurrentWeatherDataUseCase {
    override suspend fun invoke(query: String): NetworkResponse<CurrentWeatherResponse, Unit> {
        Timber.tag(TAG).d("send request: $query")
        return withContext(ioDispatcher) {
            weatherApi.getCurrentWeather(query)
        }
    }
}