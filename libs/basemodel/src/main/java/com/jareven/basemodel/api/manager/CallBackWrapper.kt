package com.jareven.basemodel.api.manager

import io.reactivex.Observer
import io.reactivex.disposables.Disposable

/**
 * @ClassName CallBackWrapper
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/13 0:20
 * @Version 1.0
 * 简介：RX数据回调封装类
 */
abstract class CallBackWrapper<T> : Observer<T> {
    private var isCancel = true

    override fun onSubscribe(d: Disposable) {
        onBegin(d)
    }

    override fun onNext(t: T) {
        isCancel = false
        onSuccess(t)
    }

    override fun onError(e: Throwable) {
        onError(e.message.toString(), 0)
    }

    override fun onComplete() {
        if (isCancel) {
            onCancel(isCancel)
        }
    }

    fun onBegin(d: Disposable) {

    }

    fun onCancel(isCancel: Boolean) {

    }

    abstract fun onSuccess(t: T)

    abstract fun onError(msg: String, code: Int)

}
