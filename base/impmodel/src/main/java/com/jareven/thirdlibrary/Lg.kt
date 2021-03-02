package com.jareven.thirdlibrary

import com.blankj.utilcode.util.LogUtils

/**
 * @ClassName Lg
 * @Author zjw
 * @Date 2021/3/1 10:38
 * 简介：日志打印
 */
class Lg {
    companion object {
        fun d(msg: String) {
            LogUtils.d(msg)
        }

        fun e(msg: String) {
            LogUtils.e(msg)
        }
    }
}