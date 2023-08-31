package com.example.theweatherapp.presentation
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.theweatherapp.core.usecaseextensions.onError
import com.example.theweatherapp.core.usecaseextensions.onSuccess
import com.example.theweatherapp.domain.state.MainUiState
import com.faceless_squad.exchangerates.domain.usecase.GetWeatherByCity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.*
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor (private val getWeatherByCity: GetWeatherByCity) : ViewModel() {

    private val _mainUiState = MutableStateFlow(
    MainUiState(
        loading = false,
        weather = null,
        events = listOf()
        )
    )

    val mainUiState = _mainUiState.asStateFlow()

    fun getWeather(city: String) {
        getWeatherByCity(city = city)
            .onStart {
                _mainUiState.update { state ->
                    state.copy(loading = true)
                }
            }
            .onSuccess {
                _mainUiState.update { state ->
                    state.copy(loading = false, weather = it)

                }
            }
            .onError {
                _mainUiState.update { state ->
                    state.copy(loading = false) + MainUiState.Event.Error(it.message ?: "Wrong city name, please, try again")
                }
            }
            .launchIn(viewModelScope)
    }
}