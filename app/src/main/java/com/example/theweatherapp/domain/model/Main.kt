package com.example.theweatherapp.domain.model


import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Main(
    @SerializedName("feels_like")
    val feelsLike: Double? = null,
    @SerializedName("grnd_level")
    val grndLevel: Int? = null,
    @SerializedName("humidity")
    val humidity: Int,
    @SerializedName("pressure")
    val pressure: Int,
    @SerializedName("sea_level")
    val seaLevel: Int? = null,
    @SerializedName("temp")
    val temp: Double,
    @SerializedName("temp_max")
    val tempMax: Double? = null,
    @SerializedName("temp_min")
    val tempMin: Double? = null
)