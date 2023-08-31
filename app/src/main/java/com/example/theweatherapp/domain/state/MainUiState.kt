package com.example.theweatherapp.domain.state

import android.media.metrics.Event
import com.example.theweatherapp.domain.model.Weather
import com.example.theweatherapp.domain.model.WeatherModel

data class MainUiState(
    val loading: Boolean,
    val weather: WeatherModel?= null,
    val events: List<Event>
) {
    sealed interface Event{
    data class Error(val message: String): Event
    }

    operator fun minus(event: Event) = this.copy(events = this.events - event)
    operator fun plus(event: Event) = this.copy( events = this.events + event)
}