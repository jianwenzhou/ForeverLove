package com.richinfo.homemodel.activity.main.found

import androidx.annotation.NonNull
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelStoreOwner
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.mvp.BasePresenter

/**
 * @ClassName FoundPresenter
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/5 18:58
 * @Version 1.0
 * 简介：发现页面的P层
 */
class FoundPresenter(@NonNull owner: ViewModelStoreOwner) : BasePresenter() {



    /**
     * 获取天气信息
     */
    fun getSimpleWeather(city: String) {

    }


    override fun onCreate(owner: LifecycleOwner) {
        LogUtils.d("FoundPresenter onCreate")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        LogUtils.d("FoundPresenter onDestroy")
    }

}