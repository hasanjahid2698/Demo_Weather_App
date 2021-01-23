package com.example.weatherapp.domain.mappers

import com.example.weatherapp.data.Forecast
import com.example.weatherapp.data.ForecastResult
import com.example.weatherapp.domain.model.ForecastList
import java.text.DateFormat
import java.util.*
import java.util.concurrent.TimeUnit
import com.example.weatherapp.domain.model.Forecast as ModelForecast

/**
 * This data mapper class  receives the Forecast data set and return a list of Forecast
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/

class ForecastDataMapper {

    /**
     *This method convert ForecastResult into ForecastList.
     *
     *
     * @param ForecastResult
     * @return ForecastList
     */

    fun convertFromDataModel(forecast: ForecastResult): ForecastList{
        return ForecastList(forecast.city.name, forecast.city.country, convertForecastListToDomain(forecast.list))
    }

    /**
     * This method convert list of Forecast into list of ModelForecast
     *
     *
     * @param List<Forecast>
     * @return List<ModelForecast>
     */
    private fun convertForecastListToDomain(list: List<Forecast>): List<ModelForecast>{
        return list.mapIndexed { i, forecast ->
            val dt = Calendar.getInstance().timeInMillis+TimeUnit.DAYS.toMillis(i.toLong())
            convertForecastListItemToDomain(forecast.copy(dt=dt))
        }
    }

    /**
     * This method convert Forecast into ModelForecast.
     * Forecast has many attributes but all are not necessary for the project.
     * So this Forecast to ModelForecast conversion is necessary
     *
     *
     * @param Forecast
     * @return ModelForecast
     */

    private  fun convertForecastListItemToDomain(forecast: Forecast):ModelForecast{
        return ModelForecast(convertDate(forecast.dt),
            forecast.weather[0].description, forecast.temp.max.toInt(),forecast.temp.min.toInt(),generateIconUrl(forecast.weather[0].icon))
    }

    /**
     * This method convert the Long date attribute to perfect String DateFormat
     *
     *
     * @param Long date
     * @return String date
     */

    private fun convertDate(date: Long): String{
        val df = DateFormat.getDateInstance(DateFormat.MEDIUM, Locale.getDefault())
        return df.format(date)
    }

    /**
     * This method convert iconCode to link of the Icon
     *
     *
     * @param iconCode
     * @return Icon_URL
     */

    private fun generateIconUrl(iconCode: String): String = "https://openweathermap.org/img/w/$iconCode.png"
}