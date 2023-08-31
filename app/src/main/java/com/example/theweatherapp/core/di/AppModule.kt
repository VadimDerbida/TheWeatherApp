package com.example.theweatherapp.core.di

import com.example.theweatherapp.data.network.WeatherApi
import com.example.theweatherapp.domain.datasource.implementation.WeatherDataSource
import com.example.theweatherapp.domain.model.Weather
import com.example.theweatherapp.domain.model.WeatherModel
import com.faceless_squad.exchangerates.domain.datasource.contract.WeatherInterface
import com.faceless_squad.exchangerates.domain.usecase.GetWeatherByCity
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun provideHttpClient(
    ): OkHttpClient {
        val clientBuilder = OkHttpClient.Builder()
            .connectTimeout(30, TimeUnit.SECONDS)
            .readTimeout(20, TimeUnit.SECONDS)
            .writeTimeout(20, TimeUnit.SECONDS)

        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        clientBuilder.addInterceptor(logging)
        return clientBuilder.build()
    }

    @Provides
    @Singleton
   fun providesRetrofit(
        client: OkHttpClient,
        converter: Converter.Factory
    ): Retrofit {
        return Retrofit.Builder()
            .client(client)
            .baseUrl("http://api.openweathermap.org/")
            .addConverterFactory(converter)
            .addConverterFactory(GsonConverterFactory.create())
            .build()


    }



    @Provides
    @Singleton
    fun providesApi(retrofit: Retrofit): WeatherApi {
        return retrofit.create(WeatherApi::class.java)
    }

    @Provides
    @Singleton
    fun providesDataSource(api: WeatherApi): WeatherInterface {
        return WeatherDataSource(api = api)
    }

    @Provides
    @Singleton
    fun providesGetWeatherByCity(weatherInterface: WeatherInterface): GetWeatherByCity {
        return GetWeatherByCity(weatherInterface)
    }

    @Provides
    @Singleton
    fun provideJson(): Json {
        return Json {
            encodeDefaults = true
            ignoreUnknownKeys = true
            isLenient = true
        }
    }

    @Provides
    @Singleton
    fun provideJsonConverter(json: Json): Converter.Factory {
        return json.asConverterFactory("application/json".toMediaType())
    }
}




