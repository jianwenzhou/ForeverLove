package com.jareven.basemodel.base

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.callback.PermissionCallback
import com.yanzhenjie.permission.AndPermission

/**
 * 简介：Activity基类
 */
abstract class BaseActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutID())
        initStatusBar()
        initActivity()
    }

    private fun initActivity() {
        //打印当前Activity名称
        LogUtils.d("BaseActivity:" + javaClass.simpleName)
        //Activity管理器
        ActivityCollector.addActivity(this)
        //传递参数注入
        ARouter.getInstance().inject(this);

        //子类初始化
        initView()
        initData()
    }

    //可重写,设置状态栏
    open fun initStatusBar() {

    }

    //可重写,初始化数据
    open fun initData() {}

    //可重写,初始化控件
    open fun initView() {}

    abstract fun getLayoutID(): Int

    override fun onDestroy() {
        super.onDestroy()
        ActivityCollector.removeActivity(this)
    }


    /**
     * 定位权限申请
     */
    protected fun requestPermission(callback: PermissionCallback, vararg permissions: String) {
        AndPermission.with(this)
            .runtime()
            .permission(permissions)
            .onGranted {
                callback.onGranted()
            }
            .onDenied {
                callback.onDenied()
            }
            .start()
    }

    /**
     * 路由跳转
     */
    protected fun routerJump(path: String) {
        ARouter.getInstance()
            .build(path)
            .navigation()
    }


    /**
     * 路由跳转,带入参 Parcelable
     */
    protected fun routerJump(path: String, key: String, value: Parcelable) {
        ARouter.getInstance()
            .build(path)
            .withParcelable(key, value)
            .navigation()
    }

    /**
     * 路由跳转,带入参 Bundle
     */
    protected fun routerJump(path: String, key: String, value: Bundle) {
        ARouter.getInstance()
            .build(path)
            .withBundle(key, value)
            .navigation()
    }

}