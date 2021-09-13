package com.png.interview.weather.di

import com.png.interview.api.common_model.NetworkResponseAdapterFactory
import com.png.interview.dagger.scope.ApplicationScope
import com.png.interview.weather.api.WeatherApi
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import timber.log.Timber

@Module
class WeatherApiModule {

    @Provides
    @ApplicationScope
    fun provideWeatherApi(
        okHttpClientBuilder: OkHttpClient.Builder,
        moshiBuilder: Moshi.Builder
    ): WeatherApi =
        Retrofit.Builder()
            .addConverterFactory(
                MoshiConverterFactory
                    .create(
                        moshiBuilder.build()
                    )
            )
            .addCallAdapterFactory(NetworkResponseAdapterFactory())
            .client(
                okHttpClientBuilder
                    .addInterceptor { chain ->
                        val request = chain.request()
                        chain.proceed(
                            request
                                .newBuilder()
                                .url(request.url.newBuilder().addQueryParameter("key", "eefb6cac72f9469984e00421212506").build())
                                .build()
                        )
                    }
                    .addInterceptor(
                        HttpLoggingInterceptor {
                            Timber.tag("OkHttp").d(it)
                        }.apply {
                            level = HttpLoggingInterceptor.Level.BASIC
                        }) // let's log http request..
                    .build()
            )
            .baseUrl(BASE_URL)
            .build()
            .create(WeatherApi::class.java)

    companion object {
        private const val BASE_URL = "http://api.weatherapi.com/v1/"
    }
}
