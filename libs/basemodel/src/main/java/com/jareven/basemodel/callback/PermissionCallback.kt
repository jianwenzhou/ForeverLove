package com.jareven.basemodel.callback

/**
 * @ClassName PermissionCallback
 * @Description TODO
 * @Author zjw
 * @Date 2020/11/9 20:59
 * @Version 1.0
 * 简介：
 */

abstract class PermissionCallback {

    abstract fun onGranted()

    open fun onDenied() {}

}