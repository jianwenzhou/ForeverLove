package com.richinfo.homemodel.activity.splash

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters
import com.jareven.thirdlibrary.Lg

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
        Lg.d("zjw  doWork:$name")
        return Result.success()
    }

    override fun onStopped() {
        super.onStopped()
        val name = Thread.currentThread().name
        Lg.d("zjw  onStopped:$name")
    }
}