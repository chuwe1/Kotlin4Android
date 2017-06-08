package com.github.chuwe1.kotlin4android.net

import com.github.chuwe1.kotlin4android.net.apiservice.WeatherApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class NetWork {

    //Kotlin没有静态的概念，只有伴生对象
    companion object {
        //单例
        @Volatile var instance: NetWork? = null
            get() {
                if (field == null) {
                    synchronized(NetWork::class.java) {
                        field = NetWork()
                    }
                }
                return field
            }

        val BASE_URL = "http://www.weather.com.cn/"

        private var retrofit: Retrofit? = null
            get() {
                if (field == null) {
                    val builder = OkHttpClient.Builder()
                    builder.connectTimeout(7, TimeUnit.SECONDS)
                    builder.writeTimeout(3, TimeUnit.SECONDS)
                    builder.readTimeout(4, TimeUnit.SECONDS)
                    //log信息拦截器
                    val loggingInterceptor = HttpLoggingInterceptor()
                    loggingInterceptor.level = HttpLoggingInterceptor.Level.BODY
                    builder.addInterceptor(loggingInterceptor)

                    field = Retrofit.Builder()
                            .client(builder.build())
                            .baseUrl(BASE_URL)
                            .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                            .addConverterFactory(GsonConverterFactory.create())
                            .build()
                }
                return field
            }



        var weatherApiService: WeatherApiService? = null
            get() {
                if (field == null) {
                    field = retrofit?.create(WeatherApiService::class.java)
                }
                return field
            }

    }

}

