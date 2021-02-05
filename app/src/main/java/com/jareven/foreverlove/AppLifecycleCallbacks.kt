package com.jareven.foreverlove

import android.app.Activity
import android.app.Application
import android.os.Bundle

/**
 * @ClassName AppLifecycleCallbacks
 * @Author zjw
 * @Date 2021/2/2 15:28
 * 简介：监听APP生命周期
 */
class AppLifecycleCallbacks : Application.ActivityLifecycleCallbacks {
    override fun onActivityPaused(activity: Activity) {
    }

    override fun onActivityStarted(activity: Activity) {
    }

    override fun onActivityDestroyed(activity: Activity) {
    }

    override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {
    }

    override fun onActivityStopped(activity: Activity) {
//        val appForeground = AppUtils.isAppForeground()
//        if (!appForeground){
//            val flag = SPUtils.getInstance().getInt("AppIconKey",0)
//            MyAppUtils.getInstence().switchAppIcon(activity,false,flag)
//            MyAppUtils.getInstence().switchAppIcon(activity,true,flag)
//        }
    }

    override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {
    }

    override fun onActivityResumed(activity: Activity) {
    }
}