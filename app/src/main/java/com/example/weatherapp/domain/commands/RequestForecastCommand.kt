package com.example.weatherapp.domain.commands

import com.example.weatherapp.data.ForecastRequest
import com.example.weatherapp.domain.mappers.ForecastDataMapper
import com.example.weatherapp.domain.model.ForecastList

/**
 * This RequestForecastCommand class execute a request command to download forecast data from
 * the https://api.openweathermap.org/ api and pass it to ForecastDataMapper class to create Forecast List
 * and return that list
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/

class RequestForecastCommand(private val zipCode: String, private val countryCode: String): Command<ForecastList>{
    override fun execute(): ForecastList {
        val forecastRequest = ForecastRequest(zipCode, countryCode)
        return ForecastDataMapper().convertFromDataModel(forecastRequest.execute())
    }
}