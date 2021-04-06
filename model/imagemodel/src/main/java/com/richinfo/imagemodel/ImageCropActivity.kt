package com.richinfo.imagemodel

import android.graphics.Color
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.richinfo.httpmodel.api.entity.Hit
import kotlinx.android.synthetic.main.imagemodel_activity_image_crop.*

@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
class ImageCropActivity : BaseActivity() {

    @JvmField
    @Autowired
    var bundle: Bundle? = null

    override fun getLayoutID(): Int {
        return R.layout.imagemodel_activity_image_crop
    }

    override fun initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, false)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
    }

    override fun initData() {
        setData()
    }

    private fun setData() {
        expandClickArea(activity_image_crop_back_iv)
        activity_image_crop_back_iv.setOnClickListener { finish() }


        bundle?.let {

            val data = it.getParcelableArrayList<Hit>(BundleConst.HOMEMODEL_LARGE_IMAGE_LIST_KEY)

            val position: Int = it.getInt(BundleConst.HOMEMODEL_LARGE_POSITION_KEY, 0)

            val adapter = ImageViewPagerAdapter(this, data as ArrayList<Hit>)
            imagemodel_viewpager.offscreenPageLimit = 3
            imagemodel_viewpager.orientation = ViewPager2.ORIENTATION_VERTICAL;
            imagemodel_viewpager.adapter = adapter
            //设置viewpager位置
            imagemodel_viewpager.setCurrentItem(position, false)
        }
    }


    override fun onWindowFocusChanged(hasFocus: Boolean) {
        super.onWindowFocusChanged(hasFocus)
        if (hasFocus) {
            hideSystemUI()
        }
    }

    private fun hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        val decorView = window.decorView
        decorView.systemUiVisibility =
            (View.SYSTEM_UI_FLAG_IMMERSIVE // Set the content to appear under the system bars so that the
                    // content doesn't resize when the system bars hide and show.
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN // Hide the nav bar and status bar
                    or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private fun showSystemUI() {
        val decorView = window.decorView
        decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN)
    }

}