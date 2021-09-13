package com.png.interview.weather.ui.viewmodel

import android.content.SharedPreferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.png.interview.weather.domain.CreateCurrentWeatherRepFromQueryUseCase
import com.png.interview.weather.domain.CreateLocationRepFromQueryUseCase
import com.png.interview.weather.ui.model.CurrentWeatherViewRepresentation
import com.png.interview.weather.ui.model.LocationViewRepresentation
import com.png.interview.weather.ui.model.MeasureUnit
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch
import java.util.*
import javax.inject.Inject

class CurrentWeatherViewModel @Inject constructor(
    private val createCurrentWeatherRepFromQueryUseCase: CreateCurrentWeatherRepFromQueryUseCase,
    private val createLocationRepFromQueryUseCase: CreateLocationRepFromQueryUseCase
) : ViewModel() {

    private val _currentWeatherViewRepresentation = MutableStateFlow<CurrentWeatherViewRepresentation>(CurrentWeatherViewRepresentation.Empty)
    private val _locationViewRepresentation = MutableStateFlow<LocationViewRepresentation>(LocationViewRepresentation.Empty)
    var query: String = ""
    var seeForecastClicked = false

    fun submitCurrentWeatherSearch(query: String, unit: MeasureUnit) {
        this.query = query
        viewModelScope.launch {
            _currentWeatherViewRepresentation.value = createCurrentWeatherRepFromQueryUseCase(query, unit)
        }
    }

    fun submitLocationSearch(query: String) {
        viewModelScope.launch {
            _locationViewRepresentation.value = createLocationRepFromQueryUseCase(query)
        }
    }

    val availableCurrentWeatherLiveData =
        _currentWeatherViewRepresentation
            .map { (it as? CurrentWeatherViewRepresentation.AvailableWeatherViewRep)?.data }
            .asLiveData()

    val isEmptyVisible =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Empty }
            .asLiveData()

    val isError =
        _currentWeatherViewRepresentation
            .map { it is CurrentWeatherViewRepresentation.Error }
            .asLiveData()

    val availableLocationLiveData =
        _locationViewRepresentation
            .map { (it as? LocationViewRepresentation.AvailableLocationViewRep)?.data }
            .asLiveData()
}