package com.jareven.foreverlove.activity.main.found

import com.jareven.basemodel.base.BaseLazyFragment
import com.jareven.foreverlove.R

/**
 * @ClassName FoundFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：发现Fragment
 */
class FoundFragment : BaseLazyFragment() {
    //延迟初始化
    lateinit var presenter: FoundPresenter

    override fun getLayoutID(): Int {
        return R.layout.fragment_main_found
    }


    override fun initData() {
        presenter = FoundPresenter(this)
        lifecycle.addObserver(presenter)

        presenter.getSimpleWeather("深圳")
    }


}