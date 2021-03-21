package com.jareven.basemodel.base

/**
 * @ClassName BaseLazyFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/8/13 15:32
 * @Version 1.0
 * 简介：懒加载Fragment
 */
abstract class BaseLazyFragment : BaseFragment() {


    private var isLoaded = false

    override fun onResume() {
        super.onResume()
        //增加了Fragment是否可见的判断
        if (!isLoaded && !isHidden) {
            lazyInit()
            isLoaded = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        isLoaded = false
    }

    abstract fun lazyInit()

}