package com.example.weatherapp.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.data.ForecastRequest
import com.example.weatherapp.domain.commands.RequestForecastCommand
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.ui.adapters.ForecastListAdapter
import org.jetbrains.anko.*


/**
 * This class accesses the RecyclerView of it's activity and after getting the data doing the data request command,
 * assign the data to the RecyclerView
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val forecastList: RecyclerView = find(R.id.forecastList)
        forecastList.layoutManager = LinearLayoutManager(this)
        val zipCode = "1311"
        val countryCode = "bd"


        /**
         * This method Request for data but not in main thread and Sync the context
         *
         */

        doAsync {
            val result = RequestForecastCommand(zipCode, countryCode).execute()
            uiThread {
                forecastList. adapter = ForecastListAdapter(result) { forecast -> toast(forecast.date)}
            }
        }
    }
}