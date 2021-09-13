package com.png.interview.weather.domain

import com.png.interview.api.common_model.NetworkResponse
import com.png.interview.weather.ui.model.AvailableLocationViewData
import com.png.interview.weather.ui.model.LocationViewRepresentation
import javax.inject.Inject

interface CreateLocationRepFromQueryUseCase {
    suspend operator fun invoke(query: String): LocationViewRepresentation
}

class DefaultCreateLocationRepFromQueryUseCase @Inject constructor(
    private val getLocationDataUseCase: GetLocationDataUseCase
) : CreateLocationRepFromQueryUseCase {
    override suspend fun invoke(query: String): LocationViewRepresentation {
        return when (val result = getLocationDataUseCase(query)) {
            is NetworkResponse.Success -> {
                LocationViewRepresentation.AvailableLocationViewRep(
                    data = result.body.map {
                        AvailableLocationViewData(
                            id = it.id,
                            name = it.name,
                            region = it.region,
                            country = it.country,
                            lat = it.lat,
                            lon = it.lon,
                            url = it.url
                        )
                    }
                )
            }
            else -> {
                LocationViewRepresentation.Error
            }
        }
    }
}