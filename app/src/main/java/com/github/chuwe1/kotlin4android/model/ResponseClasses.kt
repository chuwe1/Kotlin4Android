package com.github.chuwe1.kotlin4android.model

import com.github.chuwe1.kotlin4android.app.BaseModel

data class User(var userId: String,
                var username: String,
                var userNick: String,
                var avatar: String)

data class WeatherResponse(var weatherinfo: Weather?) : BaseModel()

data class Weather(var city: String,
                   var cityid: String,
                   var temp: String,
                   var WD: String,
                   var WS: String,
                   var SD: String,
                   var WSE: String,
                   var time: String,
                   var isRadar: String,
                   var njd: String,
                   var qy: String) {

    override fun toString(): String {
        return "city:   $city\n" +
                "cityid: $cityid\n" +
                "temp:   $temp\n" +
                "WD:     $WD\n" +
                "WS:     $WS\n" +
                "SD:     $SD\n" +
                "WSE:    $WSE\n" +
                "time:   $time\n" +
                "isRadar:$isRadar\n" +
                "njd:    $njd\n" +
                "qy:     $qy"
    }
}