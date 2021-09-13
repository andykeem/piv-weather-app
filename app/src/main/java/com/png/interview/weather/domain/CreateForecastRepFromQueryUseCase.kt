package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableForecastViewData
import com.png.interview.weather.ui.model.ForecastViewRepresentation
import com.png.interview.weather.ui.model.MeasureUnit
import javax.inject.Inject

interface CreateForecastRepFromQueryUseCase {
    suspend operator fun invoke(query: String, days: Int, unit: MeasureUnit): ForecastViewRepresentation
}

class DefaultCreateForecastRepFromQueryUseCase @Inject constructor(
    private val getForecastDataUseCase: GetForecastDataUseCase
) : CreateForecastRepFromQueryUseCase {
    override suspend fun invoke(query: String, days: Int, unit: MeasureUnit): ForecastViewRepresentation {
        return when (val result = getForecastDataUseCase(query, days)) {
            is NetworkResponse.Success -> {
                ForecastViewRepresentation.AvailableForecastViewRep(
                    data = result.body.forecast.forecastday.map {
                        AvailableForecastViewData(
                            condition = it.day.condition.text,
                            date = it.date,
                            maxtemp_c = it.day.maxtemp_c,
                            maxtemp_f = it.day.maxtemp_f,
                            mintemp_c = it.day.mintemp_c,
                            mintemp_f = it.day.mintemp_f,
                            maxwind_kph = it.day.maxwind_kph,
                            maxwind_mph = it.day.maxwind_mph,
                            unit = unit
                        )
                    }
                )
            }
            else -> {
                ForecastViewRepresentation.Error
            }
        }
    }
}