package com.jareven.basemodel.base

import android.os.Bundle
import android.os.Parcelable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.alibaba.android.arouter.launcher.ARouter

/**
 * @ClassName BaseFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/15 23:40
 * @Version 1.0
 * 简介：BaseFragment基类
 */
abstract class BaseFragment : Fragment(){

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = View.inflate(activity, getLayoutID(), null)
        initView(view)
        return view
    }



    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    /**
     * 子类必须实现，传递布局
     */
    abstract fun getLayoutID(): Int

    abstract fun initView(view: View?)

    open fun initData() {}

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