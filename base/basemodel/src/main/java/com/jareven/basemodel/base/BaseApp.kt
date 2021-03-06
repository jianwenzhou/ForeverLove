package com.jareven.basemodel.base

import android.app.Application
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.blankj.utilcode.util.Utils
import com.jareven.basemodel.BuildConfig

/**
 * Application基类
 */
open class BaseApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initLog()
        initRouter()
    }

    private fun initRouter() {
        if (BuildConfig.DEBUG) {   // 这两行必须写在init之前，否则这些配置在init过程中将无效
            ARouter.openLog()     // 打印日志
            ARouter.openDebug()   // 开启调试模式(如果在InstantRun模式下运行，必须开启调试模式！线上版本需要关闭,否则有安全风险)
        }
        ARouter.init(this) // 尽可能早，推荐在Application中初始化
    }

    /**
     * log输出
     */
    private fun initLog() {
        Utils.init(this)
        LogUtils.getConfig().globalTag = "Love"
    }

    override fun onTerminate() {
        super.onTerminate()
        ARouter.getInstance().destroy()
    }

}