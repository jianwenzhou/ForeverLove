package com.jareven.foreverlove.activity.splash

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.blankj.utilcode.util.LogUtils

/**
 * @ClassName LoginWorker
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/8 16:48
 * @Version 1.0
 * 简介：
 */
class LoginWorker(context: Context, workerParams: WorkerParameters) :
    Worker(context, workerParams) {
    override fun doWork(): Result {
        val name = Thread.currentThread().name
        LogUtils.d("zjw  doWork:$name")
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        val name = Thread.currentThread().name
        LogUtils.d("zjw  onStopped:$name")
    }
}