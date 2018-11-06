package com.samilaltin.weatherapp

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.os.Build
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v4.app.ActivityCompat
import android.view.View
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.samilaltin.weatherapp.webapi.APIClass.API_KEY
import com.samilaltin.weatherapp.webapi.MyCallback
import com.samilaltin.weatherapp.webapi.pojos.WeatherDataResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Response


class MainActivity : AppCompatActivity() {

    private var client: FusedLocationProviderClient? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
    }

    private fun init() {
        WeatherAppSingleton.instance?.init(this@MainActivity, findViewById(android.R.id.content))
        if (hasNetwork()!!) {
            client = LocationServices.getFusedLocationProviderClient(this)
            locationRequestPermission()
            if (layoutConnectionProblem.isShown) {
                layoutConnectionProblem.visibility = View.GONE
            }
        } else {
            WeatherAppSingleton.instance?.showSnackBarOrToast(
                getString(R.string.check_internet_connection_warning)
            )
            layoutConnectionProblem.visibility = View.VISIBLE
            cLayout.setOnClickListener { init() }
        }
    }

    private fun hasNetwork(): Boolean? {
        var isConnected: Boolean? = false
        val connectivityManager = this.getSystemService(Context.CONNECTIVITY_SERVICE) as
                ConnectivityManager
        val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected)
            isConnected = true
        return isConnected
    }

    private fun checkLocationPermission(): Boolean {
        return if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            !(checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(
                Manifest.permission.ACCESS_COARSE_LOCATION
            ) != PackageManager.PERMISSION_GRANTED)
        } else true
    }

    private fun locationRequestPermission() {
        ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.ACCESS_FINE_LOCATION), 1)
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when (requestCode) {
            1 -> {
                if (!checkLocationPermission()) {
                    if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(
                            this@MainActivity,
                            arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                            ),
                            2
                        )
                    }
                }

                client!!.lastLocation.addOnSuccessListener(this@MainActivity) { location ->
                    if (location != null) {
                        val lat = location.latitude.toString()
                        val long = location.longitude.toString()
                        val latLong = "$lat,$long"
                        callService(latLong)
                    }
                }
            }

            2 -> {
                if (grantResults.isEmpty() && grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    WeatherAppSingleton.instance?.showSnackBarOrToast(getString(R.string.location_permission_warning))
                    return
                }
            }

        }

    }

    private fun callService(latLong: String) {
        val apiKey = API_KEY
        WeatherAppSingleton.instance?.getWeatherWebAPI()!!.doGetWeatherResource("$apiKey/$latLong")
            .enqueue(object : MyCallback<WeatherDataResponse>() {
                override fun onResponse(call: Call<WeatherDataResponse>, response: Response<WeatherDataResponse>) {
                    val weatherDataResponse = response.body()
                }
            })
    }

}
