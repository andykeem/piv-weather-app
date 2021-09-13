package com.png.interview.weather.ui.model

sealed class ForecastViewRepresentation {
    class AvailableForecastViewRep(val data: List<AvailableForecastViewData>) : ForecastViewRepresentation()
    object Empty : ForecastViewRepresentation()
    object Error : ForecastViewRepresentation()
}
