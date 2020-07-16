package com.jareven.basemodel.base

import android.app.Activity
import android.os.Process

/**
 * 简介：Activity管理类 单例
 */
object ActivityCollector {

    private val activitys = ArrayList<Activity>()

    fun addActivity(activity: Activity) {
        activitys.add(activity)
    }

    fun removeActivity(activity: Activity) {
        activitys.remove(activity)
    }

    fun finishAll() {
        for (activity in activitys) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        activitys.clear()
        //杀死进程
        Process.killProcess(Process.myPid())
    }

}