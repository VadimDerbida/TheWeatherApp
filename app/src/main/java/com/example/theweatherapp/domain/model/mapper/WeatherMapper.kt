package com.faceless_squad.exchangerates.domain.model.exchange.mapper

import com.example.theweatherapp.domain.model.*

fun WeatherModel.toModel(): WeatherModel {
    val base = this.base
    val clouds= this.clouds
    val cod = this.cod
    val coord = this.coord
    val dt = this.dt
    val id = this.id
    val main = this.main
    val name = this.name
    val sys = this.sys
    val timezone = this.timezone
    val visibility = this.visibility
    val weather = this.weather
    val wind = this.wind
    return WeatherModel(
        base = base,
        clouds= clouds,
        cod = cod,
        coord = coord,
        dt = dt,
        id = id,
        main = main,
        name = name,
        sys = sys,
        timezone = timezone,
        visibility = visibility,
        weather = weather,
        wind = wind
    )
}

fun WeatherModel.toResponse(): WeatherModel {
    val base = this.base
    val clouds= this.clouds
    val cod = this.cod
    val coord = this.coord
    val dt = this.dt
    val id = this.id
    val main = this.main
    val name = this.name
    val sys = this.sys
    val timezone = this.timezone
    val visibility = this.visibility
    val weather = this.weather
    val wind = this.wind
    return WeatherModel(
        base = this.base,
        clouds= this.clouds,
        cod = this.cod,
        coord = this.coord,
        dt = this.dt,
        id = this.id,
        main = this.main,
        name = this.name,
        sys = this.sys,
        timezone = this.timezone,
        visibility = this.visibility,
        weather = this.weather,
        wind = this.wind
    )
}