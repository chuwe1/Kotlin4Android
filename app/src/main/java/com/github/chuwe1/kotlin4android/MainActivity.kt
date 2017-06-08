package com.github.chuwe1.kotlin4android

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.chuwe1.kotlin4android.app.BaseActivity
import com.github.chuwe1.kotlin4android.main.MainPresenter
import com.github.chuwe1.kotlin4android.main.MainView
import com.github.chuwe1.kotlin4android.model.WeatherResponse
import rx.Subscription

class MainActivity : BaseActivity<MainPresenter>(), MainView {

    override fun newPresenter(): MainPresenter {
        return MainPresenter(this)
    }

    var tv_weather: TextView? = null

    var subscription: Subscription? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn_getWeather = findViewById(R.id.btn_getWeather) as Button
        tv_weather = findViewById(R.id.tv_weather) as TextView

        btn_getWeather.setOnClickListener {
            showLoadingDialog()
            subscription = mPresenter?.getWeather()
            addSubscription(subscription)
        }
    }

    override fun updateTextViewInfo(weatherResponse: WeatherResponse) {
        tv_weather?.text = weatherResponse.weatherinfo.toString()
    }

    override fun setFailure() {
        tv_weather?.text = "加载失败"
    }

    override fun onLoadingDialogCancel() {
        super.onLoadingDialogCancel()
        removeSubscription(subscription)
    }
}

