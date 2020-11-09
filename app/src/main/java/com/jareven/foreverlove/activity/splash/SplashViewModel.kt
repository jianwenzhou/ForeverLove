package com.jareven.foreverlove.activity.splash

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jareven.basemodel.api.entity.WeatherEntity
import com.jareven.basemodel.api.manager.ApiFactory
import com.jareven.basemodel.api.manager.CallBackWrapper
import com.jareven.foreverlove.const.NetConst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @ClassName SplashViewModel
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/6 1:21
 * @Version 1.0
 * 简介：闪屏页，获取天气信息
 */
class SplashViewModel : ViewModel() {
    private var weatherLiveData: MutableLiveData<WeatherEntity> = MutableLiveData()

    fun getWeatherLiveData (): MutableLiveData<WeatherEntity> {
        return weatherLiveData
    }

    fun getSimpleWeather(city: String) {

        ApiFactory.getJuHeData()
            .getSimpleWeather(city, NetConst.SimpleWeatherJuHeKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                object : CallBackWrapper<WeatherEntity>() {
                    override fun onSuccess(t: WeatherEntity) {
                        if (t.error_code == 0) {
                            weatherLiveData.value = t
                        } else {
                            onError(t.reason, t.error_code)
                        }
                    }

                })

    }


}