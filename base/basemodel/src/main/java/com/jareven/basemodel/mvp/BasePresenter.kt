package com.jareven.basemodel.mvp

import androidx.lifecycle.LifecycleOwner

/**
 * @ClassName BasePresenter
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/5 19:00
 * @Version 1.0
 * 简介：Presenter 生命周期实现类
 */
open class BasePresenter : IPresenter {


    override fun onStart(owner: LifecycleOwner) {
    }

    override fun onResume(owner: LifecycleOwner) {
    }

    override fun onPause(owner: LifecycleOwner) {
    }

    override fun onStop(owner: LifecycleOwner) {
    }

    override fun onDestroy(owner: LifecycleOwner) {
    }

    override fun onCreate(owner: LifecycleOwner) {
    }
}