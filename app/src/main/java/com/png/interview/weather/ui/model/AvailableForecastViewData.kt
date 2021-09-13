package com.png.interview.weather.ui.model

data class AvailableForecastViewData(
    val condition: String,
    val date: String,
    private val maxtemp_c: Double,
    private val maxtemp_f: Double,
    private val mintemp_c: Double,
    private val mintemp_f: Double,
    private val maxwind_kph: Double,
    private val maxwind_mph: Double,
    private val unit: MeasureUnit
) {
    val maxTemp: String
        get() = when (unit) {
            MeasureUnit.IMPERIAL -> "$maxtemp_f F"
            MeasureUnit.METRIC -> "$maxtemp_c C"
        }

    val minTemp: String
        get() = when (unit) {
            MeasureUnit.IMPERIAL -> "$mintemp_f F"
            MeasureUnit.METRIC -> "$mintemp_c C"
        }

    val windSpeed: String
        get() = when (unit) {
            MeasureUnit.IMPERIAL -> "$maxwind_mph MPH"
            MeasureUnit.METRIC -> "$maxwind_kph KPH"
        }
}