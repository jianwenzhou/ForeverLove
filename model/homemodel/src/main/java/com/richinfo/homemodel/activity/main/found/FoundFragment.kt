package com.richinfo.homemodel.activity.main.found

import android.view.View
import com.jareven.basemodel.base.BaseLazyFragment
import com.richinfo.homemodel.R

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
        return R.layout.homemodel_fragment_main_found
    }

    override fun initView(view: View?) {

    }


    override fun initData() {
        presenter = FoundPresenter()
        lifecycle.addObserver(presenter)

    }


}