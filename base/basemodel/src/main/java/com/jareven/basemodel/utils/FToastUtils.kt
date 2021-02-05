package com.jareven.basemodel.utils

import com.blankj.utilcode.util.ToastUtils

/**
 * @ClassName FToastUtil
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 16:01
 * @Version 1.0
 * 简介：Toast基类
 */
object FToastUtils {

    /***
     * 短时间展示Toast
     */
    fun showShort(message: CharSequence) {
        cancel()
        ToastUtils.showShort(message)
    }


    /**
     * 长时间展示Toast
     */
    fun showLong(message: CharSequence) {
        cancel()
        ToastUtils.showLong(message)
    }

    /**
     * 取消土司展示
     */
    fun cancel() {
        ToastUtils.cancel()
    }

}