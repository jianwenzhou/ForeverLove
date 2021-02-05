package com.richinfo.homemodel.activity.main.home


import android.view.View
import com.bumptech.glide.Glide
import com.jareven.basemodel.base.BaseLazyFragment
import com.richinfo.homemodel.R
import kotlinx.android.synthetic.main.homemodel_fragment_main_home.*

/**
 * @ClassName HomeFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：首页Fragment
 */
class HomeFragment : BaseLazyFragment() {


    override fun getLayoutID(): Int {
        return R.layout.homemodel_fragment_main_home
    }

    override fun initView(view: View?) {
    }


    override fun initData() {
        Glide.with(this).load(R.mipmap.test).into(homemodel_imageview)
    }


}