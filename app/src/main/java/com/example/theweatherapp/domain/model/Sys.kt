package com.example.theweatherapp.domain.model


import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Sys(
    @SerializedName("country")
    val country: String,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int
)