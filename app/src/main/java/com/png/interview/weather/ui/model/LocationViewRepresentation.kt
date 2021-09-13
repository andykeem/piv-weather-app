package com.png.interview.weather.ui.model

sealed class LocationViewRepresentation {
    class AvailableLocationViewRep(val data: List<AvailableLocationViewData>) : LocationViewRepresentation()
    object Empty : LocationViewRepresentation()
    object Error : LocationViewRepresentation()
}
