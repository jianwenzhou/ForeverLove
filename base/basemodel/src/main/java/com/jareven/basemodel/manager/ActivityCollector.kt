package com.jareven.basemodel.manager

import android.app.Activity
import android.os.Process

/**
 * 简介：Activity管理类 单例
 */
object ActivityCollector {

    private val mActivityTasks = ArrayList<Activity>()

    /**
     * 添加Activity
     */
    fun addActivity(activity: Activity) {
        mActivityTasks.add(activity)
    }

    /**
     * 移出Activity
     */
    fun removeActivity(activity: Activity) {
        mActivityTasks.remove(activity)
    }

    /**
     * 关闭APP
     */
    fun finishAll() {
        for (activity in mActivityTasks) {
            if (!activity.isFinishing) {
                activity.finish()
            }
        }
        mActivityTasks.clear()
        //杀死进程
        Process.killProcess(Process.myPid())
    }

}