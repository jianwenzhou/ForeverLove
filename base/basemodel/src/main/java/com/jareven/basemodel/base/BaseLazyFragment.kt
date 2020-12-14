package com.jareven.basemodel.base

import android.os.Bundle

/**
 * @ClassName BaseLazyFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/8/13 15:32
 * @Version 1.0
 * 简介：懒加载Fragment
 */
abstract class BaseLazyFragment : BaseFragment() {

    /**
     * 懒加载过
     */
    private var isLazyLoaded = false
    /**
     * Fragment的View加载完毕的标记
     */
    private var isPrepared = false

    override
    fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isPrepared = true
        //只有Fragment onCreateView好了
        //另外这里调用一次lazyLoad(）
        lazyLoad()
    }


    /**
     * 懒加载一次。如果只想在对用户可见时才加载数据，并且只加载一次数据，在子类中重写该方法
     */
    open fun onLazyLoadOnce() {

    }

    override
    fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        //只有当fragment可见时，才进行加载数据
        if (isVisibleToUser) {
            lazyLoad()
        }
    }

    private fun lazyLoad() {
        if (userVisibleHint && isPrepared && !isLazyLoaded) {
            onLazyLoadOnce()
            isLazyLoaded = true
        }
    }

    override
    fun onDestroyView() {
        super.onDestroyView()
        isLazyLoaded = false
        isPrepared = false
    }

}
