package com.jareven.foreverlove.activity.splash;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.work.Worker;
import androidx.work.WorkerParameters;

import com.blankj.utilcode.util.LogUtils;

/**
 * @ClassName LoginWorker
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/8 16:48
 * @Version 1.0
 * 简介：
 */
public class LoginWorker extends Worker {

    public LoginWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
    }

    @NonNull
    @Override
    public Result doWork() {
        String name = Thread.currentThread().getName();
        LogUtils.d("zjw  doWork:"+name);
        return  Result.success();
    }

    @Override
    public void onStopped() {
        super.onStopped();
        String name = Thread.currentThread().getName();
        LogUtils.d("zjw  onStopped:"+name);
    }
}
