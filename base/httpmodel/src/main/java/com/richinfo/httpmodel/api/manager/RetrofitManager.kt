package com.richinfo.httpmodel.api.manager

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


/**
 * @ClassName RetrofitManager
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 14:08
 * @Version 1.0
 * 简介：创建Retrofit对象
 */
object RetrofitManager {
    private const val HOST = "http://v.juhe.cn"

    fun getInstence(): Retrofit {
        return Retrofit.Builder().baseUrl(HOST)
            .client(OkHttpClient.Builder().build())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()
    }

}