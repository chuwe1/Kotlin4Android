package com.github.chuwe1.kotlin4android.main

import com.github.chuwe1.kotlin4android.app.BasePresenter
import com.github.chuwe1.kotlin4android.net.NetWork
import rx.Subscription
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*

class MainPresenter(view: MainView) : BasePresenter(view) {

    val mainView: MainView = view

    fun getWeather(): Subscription {
        return NetWork.weatherApiService!!
                .getWeatherInfo("${101190200 + Random().nextInt(4)}")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                               mainView.updateTextViewInfo(it)
                           }, {
                               mainView.hideLoadingDialog()
                               mainView.setFailure()
                           }, {
                               mainView.hideLoadingDialog()
                           })
    }
}
