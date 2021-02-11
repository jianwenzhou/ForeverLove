package com.richinfo.httpmodel.api.callback

/**
 * @ClassName DataCallback
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/6 1:33
 * @Version 1.0
 * 简介：网络数据回调接口
 */
abstract class DataCallback<T> {

    open fun onBegin() {}

    abstract fun onSuccess(t: T)

    open fun onError(msg: String, code: Int) {}

}