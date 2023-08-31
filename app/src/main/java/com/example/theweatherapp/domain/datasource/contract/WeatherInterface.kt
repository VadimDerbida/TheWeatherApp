package com.faceless_squad.exchangerates.domain.datasource.contract

import com.example.theweatherapp.core.usecaseextensions.UseCaseResult
import com.example.theweatherapp.domain.model.Weather
import com.example.theweatherapp.domain.model.WeatherModel
import kotlinx.coroutines.flow.Flow
import okhttp3.internal.connection.Exchange

interface WeatherInterface {

    fun getWeatherByCity(code: String): Flow<UseCaseResult<WeatherModel>>
}