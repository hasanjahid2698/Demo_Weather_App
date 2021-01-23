package com.example.weatherapp.ui.adapters

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.domain.model.Forecast
import com.example.weatherapp.domain.model.ForecastList
import com.example.weatherapp.ui.utils.ctx
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_forecast.view.*
import org.jetbrains.anko.find
/**
 * This is a adapter class of the recyclerview. It receives a weekForecastList and their click listener,
 * and bind that list to the recyclerview
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/

class ForecastListAdapter( private val weekForecast: ForecastList,
                            private val itemClick: (Forecast)-> Unit):
        RecyclerView.Adapter<ForecastListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val  view = LayoutInflater.from(parent.ctx).inflate(R.layout.item_forecast, parent, false)
        return ViewHolder(view, itemClick)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindForecast(weekForecast[position])
    }

    override fun getItemCount(): Int {
        return weekForecast.dailyForecast.size
    }
    class ViewHolder(view: View, val itemClick: (Forecast)->Unit): RecyclerView.ViewHolder(view){

        fun bindForecast(forecast: Forecast){
            with(forecast){
                Picasso.with(itemView.ctx).load(iconUrl).into(itemView.icon)
                itemView.date.text = date
                itemView.description.text = description
                itemView.maxTemperature.text = "$high"
                itemView.minTemperature.text = "$low"
                itemView.setOnClickListener { itemClick(this) }
            }
        }

    }
}