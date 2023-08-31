package com.example.theweatherapp.data.network

import com.example.theweatherapp.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface WeatherApi {
    //https://api.openweathermap.org/data/2.5/weather?q=bing%C3%B6l&APPID=d91871ab2a87c77a2302503f3f79f54d
 @GET("data/2.5/weather")
    suspend fun getWeatherByCity(
        @Query("q") cityName: String,
        @Query("units") unit: String = "metric",
        @Query("appid") appId: String = "d91871ab2a87c77a2302503f3f79f54d"
    ): Response<WeatherModel>
}