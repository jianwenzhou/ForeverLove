package com.jareven.basemodel.base

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RelativeLayout
import androidx.core.app.ActivityOptionsCompat
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ToastUtils

/**
 * @ClassName BaseFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/15 23:40
 * @Version 1.0
 * 简介：BaseFragment基类
 */
abstract class BaseFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return View.inflate(activity, getLayoutID(), null)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initView(view)
        initData()
    }

    /**
     * 子类必须实现，传递布局
     */
    abstract fun getLayoutID(): Int

    abstract fun initView(view: View?)

    open fun initData() {}

    /**
     * 状态栏控件
     */
    open fun setStateBarView(id: Int) {
        val group = view?.findViewById<ViewGroup>(id)
        group?.addView(createTopView())
    }


    /**
     * 创造一个透明的状态栏，避免fragment布局顶上状态栏
     */
    private fun createTopView(): View? {
        val view = View(activity)
        val statusBarHeight: Int = BarUtils.getStatusBarHeight()
        val params = RelativeLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            statusBarHeight
        )
        view.layoutParams = params
        view.setBackgroundColor(Color.TRANSPARENT)
        return view
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


    /**
     * 路由跳转,带入参 Parcelable
     * 带转场动画
     */
    protected fun routerJump(
        path: String,
        key: String,
        value: Parcelable,
        options: ActivityOptionsCompat?,
        activity: Activity
    ) {
        ARouter.getInstance()
            .build(path)
            .withParcelable(key, value)
            .withOptionsCompat(options)
            .navigation(activity)
    }

    /**
     * 路由跳转,带入参 Bundle
     * 带转场动画
     */
    protected fun routerJump(
        path: String, key: String, value: Bundle,
        options: ActivityOptionsCompat?,
        activity: Activity
    ) {
        ARouter.getInstance()
            .build(path)
            .withBundle(key, value)
            .withOptionsCompat(options)
            .navigation(activity)
    }

    /**
     * 土司展示
     */
    fun toast(msg: String) {
        ToastUtils.showShort(msg)
    }


}