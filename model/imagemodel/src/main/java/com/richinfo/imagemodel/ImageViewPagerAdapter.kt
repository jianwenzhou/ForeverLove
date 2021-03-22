package com.richinfo.imagemodel

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.richinfo.httpmodel.api.entity.Hit

/**
 * @ClassName ImageViewPagerAdapter
 * @Author zjw
 * @Date 2021/3/21 23:43
 * 简介：adapter
 */
class ImageViewPagerAdapter(fragment: FragmentActivity, private var data: ArrayList<Hit>) :
    FragmentStateAdapter(fragment) {

    override fun getItemCount(): Int {
        return data.size
    }

    override fun createFragment(position: Int): Fragment {
        return ImageCropFragment.getInstance(data[position])
    }
}