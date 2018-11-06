package com.samilaltin.weatherapp

import android.annotation.SuppressLint
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Toast
import com.samilaltin.weatherapp.webapi.APIClass
import com.samilaltin.weatherapp.webapi.WebAPI
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by saltin on 06.11.2018
 */
class WeatherAppSingleton
private constructor() {
    private var weatherWebAPI: WebAPI? = null
    private var context: Context? = null
    private var view: View? = null

    companion object {

        @SuppressLint("StaticFieldLeak")
        @Volatile
        private var ourInstance: WeatherAppSingleton? = null
        val instance: WeatherAppSingleton?
            get() {
                if (ourInstance == null) {
                    synchronized(WeatherAppSingleton::class.java) {
                        if (ourInstance == null)
                            ourInstance = WeatherAppSingleton()
                    }
                }
                return ourInstance
            }
    }

    fun init(context: Context, view: View) {
        this.context = context.applicationContext
        this.view = view
    }

    init {
        if (ourInstance != null) {
            throw RuntimeException("Use getInstance() method to get the single instance of this class.")
        }
    }

    fun showSnackBarOrToast(charSequence: CharSequence) {
        val androidVersion = android.os.Build.VERSION.SDK_INT
        if (androidVersion > 21) {
            Snackbar.make(view!!, charSequence, Snackbar.LENGTH_LONG).show()
        } else {
            Toast.makeText(context, charSequence, Toast.LENGTH_LONG).show()
        }
    }

    fun getWeatherWebAPI(): WebAPI? {
        if (weatherWebAPI == null) {
            val retrofit = Retrofit.Builder()
                .baseUrl(APIClass.API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            weatherWebAPI = retrofit.create(WebAPI::class.java)
        }
        return weatherWebAPI
    }
}