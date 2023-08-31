package com.example.theweatherapp.presentation

import android.opengl.Visibility
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.viewModels
import androidx.cardview.widget.CardView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.lifecycle.lifecycleScope
import com.example.theweatherapp.R
import com.example.theweatherapp.domain.model.WeatherModel
import com.example.theweatherapp.domain.state.MainUiState
import com.google.android.material.button.MaterialButton
import com.google.android.material.progressindicator.CircularProgressIndicator
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import retrofit2.Retrofit

const val  API_KEY = "966ddc89c57c4503bc485408221110"

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupUi()
        observeData()
    }
    private fun setupUi(){
        val searchButton = findViewById<ImageView>(R.id.img_search_city)
        val cityEditText = findViewById<TextView>(R.id.edt_city_name)
        searchButton.setOnClickListener{
            if (cityEditText.text.isNullOrBlank()){
                Toast.makeText(this, "City field cannot be empty, please, enter city", Toast.LENGTH_LONG).show()
            } else{
                viewModel.getWeather(cityEditText.text.toString().capitalize())
            }
        }
    }

    private fun observeData(){
        val loadingIndicator = findViewById<CircularProgressIndicator>(R.id.loading_indicator)
        val cityCode = findViewById<TextView>(R.id.tv_city_code)
        val cityName = findViewById<TextView>(R.id.tv_city_name)
        val temp = findViewById<TextView>(R.id.tv_degree)
        val humidity = findViewById<TextView>(R.id.tv_humidity)
        val windSpeed = findViewById<TextView>(R.id.tv_wind_speed)
        val firstLinear = findViewById<LinearLayout>(R.id.first_linear)
        val secondLinear = findViewById<CardView>(R.id.second_linear)
        lifecycleScope.launchWhenCreated {
            viewModel.mainUiState.collectLatest { state ->
                if (state.loading) {
                    loadingIndicator.visibility = View.VISIBLE
                } else {
                    loadingIndicator.visibility = View.GONE
                }

                when (state.weather) {
                    null ->{
                        firstLinear.visibility = View.GONE
                        secondLinear.visibility = View.GONE
                        cityCode.text = "No data"
                        cityName.text = "No data"
                        humidity.text = "No data"
                        windSpeed.text = "No data"
                    }
                    else -> {
                        firstLinear.visibility = View.VISIBLE
                        secondLinear.visibility = View.VISIBLE
                        cityCode.text = state.weather.name
                        cityName.text = state.weather.sys.country
                        temp.text = state.weather.main.temp.toString() + "°C"
                        humidity.text = state.weather.main.humidity.toString() + "%"
                        windSpeed.text = state.weather.wind.speed.toString() + " metre/sec"
                    }
                }
                state.events.firstOrNull()?.let { event ->
                    when (event) {
                        is MainUiState.Event.Error -> Toast.makeText(this@MainActivity, event.message, Toast.LENGTH_SHORT).show()
                    }
                }

            }
        }
    }

}