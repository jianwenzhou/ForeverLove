package com.jareven.basemodel.base

import android.app.Application
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils

/**
 * Application基类
 */
open class BaseApp :Application() {

    override fun onCreate() {
        super.onCreate()
        Utils.init(this)
        LogUtils.getConfig().globalTag = "Love"
    }

}