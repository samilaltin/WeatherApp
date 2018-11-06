package com.samilaltin.weatherapp.webapi.pojos


import com.google.gson.annotations.SerializedName


data class Hourly(
//	val any: Any? = null
	@field:SerializedName("summary")
	val summary: String? = null,

	@field:SerializedName("data")
	val data: List<DataItem?>? = null,

	@field:SerializedName("icon")
	val icon: String? = null
)