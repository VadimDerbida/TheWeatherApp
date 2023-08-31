package com.example.theweatherapp.domain.model


import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Coord(
    @SerializedName("lat")
    val lat: Double,
    @SerializedName("lon")
    val lon: Double
)