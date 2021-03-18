package com.richinfo.imagemodel

import android.graphics.Bitmap
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.*
import com.bm.library.PhotoView
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.BlurUtils.doBlur
import com.jareven.basemodel.utils.GlideUtils.loadImage

@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
class ImageCropActivity : BaseActivity() {
    @JvmField
    @Autowired
    var bundle: Bundle? = null
    private var photoView: PhotoView? = null
    private var bgView: ImageView? = null
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
        findView()
        setData()
    }

    private fun findView() {
        bgView = findViewById(R.id.imagemodel_bg_view)
        photoView = findViewById(R.id.imagemodel_photo_view)
        initPhotoView(photoView)
    }

    private fun setData() {
        val largeImageURL =
            bundle!!.getString(BundleConst.HOMEMODEL_LARGE_IMAGE_URL_KEY, defaultUrl)
        //读取缓存中的drawable
        val drawable = CacheDiskUtils.getInstance().getDrawable(largeImageURL)
        val bitmap = ConvertUtils.drawable2Bitmap(drawable)

        //模糊原图并设置背景
        doBlur(bitmap)

        //缩放，宽度铺满，高度自适应
        val scale = ImageUtils.scale(
            bitmap, ScreenUtils.getScreenWidth(),
            ScreenUtils.getScreenWidth() / bitmap.width * bitmap.height
            , true
        )

        //占位图
        val placeholder = ConvertUtils.bitmap2Drawable(scale)

        //加载原图
        loadImage(this, largeImageURL, placeholder, photoView)
    }

    /**
     * 放大并模糊
     *
     * @param bitmap 原图
     */
    private fun doBlur(bitmap: Bitmap) {
        //放大并模糊
        val bg = doBlur(bitmap, 20, 5)
        //设置给背景
        bgView!!.setImageBitmap(bg)
    }

    /**
     * photoView初始化配置
     *
     * @param photoView photoView
     */
    private fun initPhotoView(photoView: PhotoView?) {
        // 启用图片缩放功能
        photoView!!.enable()
        // 获取图片信息
        val info = photoView.info
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info)
        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
        photoView.animaTo(info) {
            //动画完成监听
        }
        // 获取/设置 动画持续时间
        photoView.animaDuring = 300
        // 获取/设置 最大缩放倍数
        photoView.maxScale = 2f
        // 设置动画的插入器
        photoView.setInterpolator(AccelerateDecelerateInterpolator())
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

    companion object {
        private const val defaultUrl =
            "https://pixabay.com/get/57e8d34b4e57a514f1dc846096293f78133fd7ec554c704f742f72d4974acc58_640.jpg"
    }
}