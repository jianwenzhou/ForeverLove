package com.jareven.basemodel.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment

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
       return View.inflate(activity,getLayoutID(),null)
    }


    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        initData()
    }

    /**
     * 子类必须实现，传递布局
     */
    abstract fun getLayoutID(): Int

    abstract fun initData()


}