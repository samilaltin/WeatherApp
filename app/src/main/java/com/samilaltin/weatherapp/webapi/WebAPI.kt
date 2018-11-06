package com.samilaltin.weatherapp.webapi

import com.samilaltin.weatherapp.webapi.pojos.WeatherDataResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Url

/**
 * Created by saltin on 30.10.2018
 */
interface WebAPI {

    @GET
    fun doGetWeatherResource(
        @Url url: String
    ): Call<WeatherDataResponse>
}