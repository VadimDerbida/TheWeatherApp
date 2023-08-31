package com.example.theweatherapp.domain.datasource.implementation

import com.example.theweatherapp.core.usecaseextensions.UseCaseResult
import com.example.theweatherapp.core.usecaseextensions.mapSuccess
import com.example.theweatherapp.core.usecaseextensions.safeFlow
import com.example.theweatherapp.data.network.WeatherApi
import com.example.theweatherapp.domain.model.WeatherModel
import com.faceless_squad.exchangerates.domain.datasource.contract.WeatherInterface
import com.faceless_squad.exchangerates.domain.model.exchange.mapper.toModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class WeatherDataSource(private val api: WeatherApi): WeatherInterface {
    override fun getWeatherByCity(city: String): Flow<UseCaseResult<WeatherModel>> = safeFlow {
        api.getWeatherByCity(cityName = city).mapSuccess { weatherModel: WeatherModel -> weatherModel.toModel() }
    }
}

