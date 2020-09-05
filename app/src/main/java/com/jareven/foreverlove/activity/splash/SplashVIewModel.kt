package com.jareven.foreverlove.activity.splash

import androidx.lifecycle.ViewModel
import com.jareven.basemodel.api.callback.DataCallback
import com.jareven.basemodel.api.entity.WeatherEntity
import com.jareven.basemodel.api.manager.ApiFactory
import com.jareven.basemodel.api.manager.CallBackWrapper
import com.jareven.foreverlove.const.NetConst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @ClassName SplashVIewModel
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/6 1:21
 * @Version 1.0
 * 简介：闪屏页，获取天气信息
 */
class SplashVIewModel : ViewModel() {

    fun getSimpleWeather(city: String,callback: DataCallback<WeatherEntity>) {

        ApiFactory.getJuHeData()
            .getSimpleWeather(city, NetConst.SimpleWeatherJuHeKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : CallBackWrapper<WeatherEntity>() {
                    override fun onSuccess(t: WeatherEntity) {
                        if (t.error_code == 0) {
                            callback.onSuccess(t)
                        } else {
                            callback.onError(t.reason,t.error_code)
                        }
                    }

                    override fun onError(msg: String, code: Int) {
                        callback.onError(msg,code)
                    }


                })

    }

}