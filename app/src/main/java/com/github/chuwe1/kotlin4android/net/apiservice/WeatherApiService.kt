package com.github.chuwe1.kotlin4android.net.apiservice

import com.github.chuwe1.kotlin4android.model.WeatherResponse
import retrofit2.http.GET
import retrofit2.http.Path
import rx.Observable

interface WeatherApiService {

    @GET("adat/sk/{cityId}.html")
    fun getWeatherInfo(@Path("cityId") cityId: String): Observable<WeatherResponse>
}
