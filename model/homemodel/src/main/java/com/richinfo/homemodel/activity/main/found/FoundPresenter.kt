package com.richinfo.homemodel.activity.main.found

import androidx.lifecycle.LifecycleOwner
import com.jareven.basemodel.mvp.BasePresenter
import com.jareven.thirdlibrary.Lg

/**
 * @ClassName FoundPresenter
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/5 18:58
 * @Version 1.0
 * 简介：发现页面的P层
 */
class FoundPresenter : BasePresenter() {

    override fun onCreate(owner: LifecycleOwner) {
        Lg.d("FoundPresenter onCreate")
    }

    override fun onDestroy(owner: LifecycleOwner) {
        Lg.d("FoundPresenter onDestroy")
    }

}