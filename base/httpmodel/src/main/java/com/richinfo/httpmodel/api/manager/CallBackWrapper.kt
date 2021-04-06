package com.richinfo.httpmodel.api.manager

import com.blankj.utilcode.util.LogUtils
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

    open fun onBegin(d: Disposable) {

    }

    open fun onCancel(isCancel: Boolean) {

    }

    abstract fun onSuccess(t: T)

    open fun onError(msg: String, code: Int) {
        LogUtils.d("code=$code---msg=$msg")
    }

}
