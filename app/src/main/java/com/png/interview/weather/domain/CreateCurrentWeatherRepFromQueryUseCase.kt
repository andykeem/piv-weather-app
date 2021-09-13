package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableWeatherViewData
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.ui.model.MeasureUnit
import javax.inject.Inject

interface CreateCurrentWeatherRepFromQueryUseCase {
    suspend operator fun invoke(query: String, unit: MeasureUnit): CurrentWeatherViewRepresentation
}

class DefaultCreateCurrentWeatherRepFromQueryUseCase @Inject constructor(
    private val getCurrentWeatherDataUseCase: GetCurrentWeatherDataUseCase
) : CreateCurrentWeatherRepFromQueryUseCase {
    override suspend fun invoke(query: String, unit: MeasureUnit): CurrentWeatherViewRepresentation {
        return when (val result = getCurrentWeatherDataUseCase(query)) {
            is NetworkResponse.Success -> {
                val resp = result.body
                CurrentWeatherViewRepresentation.AvailableWeatherViewRep(
                    AvailableWeatherViewData(
                        condition = resp.current.condition.text,
                        date = resp.location.localtime,
                        gust_kph = resp.current.gust_kph,
                        gust_mph = resp.current.gust_mph,
                        name = resp.location.name,
                        temp_c = resp.current.temp_c,
                        temp_f = resp.current.temp_f,
                        windDirection = resp.current.wind_dir,
                        unit = unit
                    )
                )
            }
            else -> {
                CurrentWeatherViewRepresentation.Error
            }
        }
    }
}