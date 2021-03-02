package com.jareven.basemodel.utils

import android.app.Activity
import android.content.ComponentName
import android.content.pm.PackageManager
import com.jareven.thirdlibrary.Lg

/**
 * @ClassName AppUtli
 * @Author zjw
 * @Date 2021/2/2 9:54
 * 简介：动态改变APP图标
 */
class MyAppUtils {

    companion object {
        fun getInstence(): MyAppUtils {
            return MyAppUtils()
        }
    }

    /**
     * 动态改变APP图标
     */
    fun switchAppIcon(context: Activity, isChange: Boolean, flag: Int) {
        Lg.d("zjw switchAppIcon=$flag")
        val pm = context.packageManager

        val aliasName = getAliasName(flag)

        //Activity入口名称
        val launchAct = "com.jareven.foreverlove.OpenActivity"

        if (isChange) {
            //关闭默认
            pm.setComponentEnabledSetting(
                ComponentName(context, launchAct),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
            //启用alias
            pm.setComponentEnabledSetting(
                ComponentName(context, aliasName),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
        } else {
            //开启默认
            pm.setComponentEnabledSetting(
                ComponentName(context, launchAct),
                PackageManager.COMPONENT_ENABLED_STATE_ENABLED,
                PackageManager.DONT_KILL_APP
            )
            //关闭alias
            pm.setComponentEnabledSetting(
                ComponentName(context, aliasName),
                PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                PackageManager.DONT_KILL_APP
            )
        }
    }

    /**
     * 通过外部传入的flag，选择不同的图标
     */
    private fun getAliasName(flag: Int): String {
        //包名+activity-alias的name
        //默认名称
        var aliasName = "com.jareven.foreverlove.OpenActivity"
        if (flag == 1) {
            //春节
            aliasName = "com.jareven.foreverlove.chunjie"
        } else if (flag == 2) {
            //中秋
            aliasName = "com.jareven.foreverlove.zhongqiu"
        }
        return aliasName
    }

}

