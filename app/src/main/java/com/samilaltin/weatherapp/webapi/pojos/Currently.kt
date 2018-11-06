package com.samilaltin.weatherapp.webapi.pojos


import com.google.gson.annotations.SerializedName


data class Currently(

	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("precipProbability")
	val precipProbability: Int? = null,

	@field:SerializedName("visibility")
	val visibility: Double? = null,

	@field:SerializedName("windGust")
	val windGust: Double? = null,

	@field:SerializedName("precipIntensity")
	val precipIntensity: Int? = null,

	@field:SerializedName("icon")
	val icon: String? = null,

	@field:SerializedName("cloudCover")
	val cloudCover: Double? = null,

	@field:SerializedName("windBearing")
	val windBearing: Int? = null,

	@field:SerializedName("apparentTemperature")
	val apparentTemperature: Double? = null,

	@field:SerializedName("pressure")
	val pressure: Double? = null,

	@field:SerializedName("dewPoint")
	val dewPoint: Double? = null,

	@field:SerializedName("ozone")
	val ozone: Double? = null,

	@field:SerializedName("temperature")
	val temperature: Double? = null,

	@field:SerializedName("humidity")
	val humidity: Double? = null,

	@field:SerializedName("time")
	val time: Int? = null,

	@field:SerializedName("windSpeed")
	val windSpeed: Double? = null,

	@field:SerializedName("uvIndex")
	val uvIndex: Int? = null
)