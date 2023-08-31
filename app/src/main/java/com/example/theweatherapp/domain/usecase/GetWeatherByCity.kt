package com.faceless_squad.exchangerates.domain.usecase

import com.example.theweatherapp.core.usecaseextensions.UseCaseResult
import com.example.theweatherapp.domain.model.WeatherModel
import com.faceless_squad.exchangerates.domain.datasource.contract.WeatherInterface
import kotlinx.coroutines.flow.Flow

class GetWeatherByCity(private val weatherInterface: WeatherInterface) {
    operator fun invoke(city: String): Flow<UseCaseResult<WeatherModel>> {
        return weatherInterface.getWeatherByCity(city)
    }
}