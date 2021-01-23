package com.example.weatherapp.ui.utils

import android.content.Context
import android.view.View

/**
 * This class to extends the properties of views and it add a ctx properties to the View
 *
 * @owner Brotecs Technologies, LLC.
 * @version 1.0.0
 * @author  Jahid Hasan
 * @since 22/12/2020
 * @apiNote Please do not change any parameters without designer consent
 **/

val View.ctx : Context
    get() = context