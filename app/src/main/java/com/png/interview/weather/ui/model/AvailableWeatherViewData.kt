package com.png.interview.weather.ui.model

data class AvailableWeatherViewData(
    val condition: String,
    val date: String,
    private val gust_kph: Double,
    private val gust_mph: Double,
    val name: String,
    private val temp_c: Double,
    private val temp_f: Double,
    val windDirection: String,
    private val unit: MeasureUnit
) {
    val temperature: String
        get() = when (unit) {
            MeasureUnit.IMPERIAL -> "$temp_f F"
            MeasureUnit.METRIC -> "$temp_c C"
        }

    val windSpeed: String
        get() = when (unit) {
            MeasureUnit.IMPERIAL -> "$gust_mph MPH"
            MeasureUnit.METRIC -> "$gust_kph KPH"
        }
}