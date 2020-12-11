package com.jareven.basemodel.util

import com.blankj.utilcode.util.ToastUtils
import com.jareven.basemodel.R

/**
 * @ClassName FToastUtil
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 16:01
 * @Version 1.0
 * 简介：Toast基类
 */
object FToastUtil {

    /***
     * 短时间展示Toast
     */
    fun showShort(message: CharSequence) {
        cancel()
        ToastUtils.setBgResource(R.drawable.basemodel_shape_rectangle_black_radius_24)
        ToastUtils.showShort(message)
    }


    /**
     * 长时间展示Toast
     */
    fun showLong(message: CharSequence) {
        cancel()
        ToastUtils.setBgResource(R.drawable.basemodel_shape_rectangle_black_radius_24)
        ToastUtils.showLong(message)
    }

    /**
     * 取消土司展示
     */
    fun cancel() {
        ToastUtils.cancel()
    }

}