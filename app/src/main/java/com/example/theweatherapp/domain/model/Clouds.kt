package com.example.theweatherapp.domain.model


import com.google.gson.annotations.SerializedName

@kotlinx.serialization.Serializable
data class Clouds(
    @SerializedName("all")
    val all: Int
)