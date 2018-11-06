package com.samilaltin.weatherapp.webapi.pojos


import com.google.gson.annotations.SerializedName


data class WeatherDataResponse(

	@field:SerializedName("currently")
	val currently: Currently? = null,

	@field:SerializedName("timezone")
	val timezone: String? = null,

	@field:SerializedName("latitude")
	val latitude: Double? = null,

	@field:SerializedName("daily")
	val daily: Daily? = null,

//	@field:SerializedName("hourly")
//	val hourly: Hourly? = null,

	@field:SerializedName("longitude")
	val longitude: Double? = null
)