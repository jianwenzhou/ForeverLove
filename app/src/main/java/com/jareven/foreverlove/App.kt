package com.jareven.foreverlove

import com.jareven.basemodel.base.BaseApp

/**
 * @ClassName App
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 1:14
 * @Version 1.0
 * 简介：APP
 */
class App : BaseApp() {


    override fun onCreate() {
        super.onCreate()
        initLifecycle()
    }


    private fun initLifecycle() {
        //绑定Activity的Rx生命周期
        registerActivityLifecycleCallbacks(AppLifecycleCallbacks())
    }

}