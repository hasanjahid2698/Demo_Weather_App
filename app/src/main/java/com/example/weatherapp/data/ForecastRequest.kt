package com.example.weatherapp.data

import android.util.Log
import com.google.gson.Gson
import java.net.URL

/**
 * This class receives a ZipCode and Download data using that ZipCode
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/

class ForecastRequest(private val zipCode: String, private val countryCode: String) {

    companion object{
        private const val APP_ID = "15646a06818f61f7b8d7823ca833e1ce"
        private const val URL = "https://api.openweathermap.org/data/2.5/forecast/daily?mode=json&units=metric&cnt=7"
        private const val COMPLETE_URL = "$URL&APPID=$APP_ID&zip="

    }

    /**
     *This method parses json data into ForecastResult.
     *
     *
     * @param
     * @return ForecastResult : result after parsing json
     */
    fun execute(): ForecastResult{
        val forecastJsonStr = URL("$COMPLETE_URL$zipCode,$countryCode").readText()
        return Gson().fromJson(forecastJsonStr, ForecastResult::class.java)
    }
}