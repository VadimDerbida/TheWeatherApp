package com.example.theweatherapp.domain.model


import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Wind(
        @SerializedName("speed")
    val speed: Double
)