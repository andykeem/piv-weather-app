package com.png.interview.weather.di

import com.png.interview.weather.domain.*
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherUseCaseModule {

    @Binds
    abstract fun bindsGetCurrentWeatherDataUseCase(usecase: DefaultGetCurrentWeatherDataUseCase): GetCurrentWeatherDataUseCase

    @Binds
    abstract fun bindsGetCurrentWeatherRepUseCase(usecase: DefaultCreateCurrentWeatherRepFromQueryUseCase): CreateCurrentWeatherRepFromQueryUseCase

    @Binds
    abstract fun bindsGetForecastDataUseCase(usecase: DefaultGetForecastDataUseCase): GetForecastDataUseCase

    @Binds
    abstract fun bindsGetForecastRepUseCase(usecase: DefaultCreateForecastRepFromQueryUseCase): CreateForecastRepFromQueryUseCase

    @Binds
    abstract fun bindCreateLocationRepFromQueryUseCase(usecase: DefaultCreateLocationRepFromQueryUseCase): CreateLocationRepFromQueryUseCase

    @Binds
    abstract fun bindGetLocationDataUseCase(usecase: DefaultGetLocationDataUseCase): GetLocationDataUseCase
}