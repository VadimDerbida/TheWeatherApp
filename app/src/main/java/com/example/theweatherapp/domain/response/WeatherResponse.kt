package com.faceless_squad.exchangerates.domain.response

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class WeatherResponse(
    @SerialName("rates") val rates: Map<String, Float>,
    @SerialName("base_code") val baseCode: String
)
