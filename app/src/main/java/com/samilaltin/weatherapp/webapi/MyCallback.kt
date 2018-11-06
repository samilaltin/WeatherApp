package com.samilaltin.weatherapp.webapi

import android.content.res.Resources
import com.samilaltin.weatherapp.R
import com.samilaltin.weatherapp.WeatherAppSingleton
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

/**
 * Created by saltin on 06.11.2018
 */
open class MyCallback<T> : Callback<T> {
    override fun onFailure(call: Call<T>, t: Throwable) {
        WeatherAppSingleton.instance?.showSnackBarOrToast(Resources.getSystem().getString(R.string.an_unexpected_error_occurred))
        call.cancel()
    }

    override fun onResponse(call: Call<T>, response: Response<T>) {

    }
}
