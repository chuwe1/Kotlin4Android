package com.github.chuwe1.kotlin4android.main

import com.github.chuwe1.kotlin4android.app.BaseView
import com.github.chuwe1.kotlin4android.model.WeatherResponse

interface MainView : BaseView {

    fun updateTextViewInfo(weatherResponse: WeatherResponse)

    fun setFailure()
}
