package com.richinfo.homemodel.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

/**
 * @ClassName MainViewPager
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:21
 * @Version 1.0
 * 简介：主页里面的ViewPager2，和底部tab绑定
 */
class MainViewPager(fragment: FragmentActivity, private var data: List<Fragment>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return data[position]
    }
}