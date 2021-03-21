package com.richinfo.imagemodel

import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ConvertUtils
import com.bm.library.PhotoView
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.BlurUtils.setBackgroue
import com.jareven.basemodel.utils.GlideUtils.loadImage
import com.richinfo.httpmodel.api.entity.Hit
import com.richinfo.uimodel.dialog.DialogManager.dismissLoadingDialog
import com.richinfo.uimodel.dialog.DialogManager.showLoadingDialog

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
        bundle?.let {

            val data = it.getParcelableArrayList<Hit>(BundleConst.HOMEMODEL_LARGE_IMAGE_LIST_KEY)

            val position: Int = it.getInt(BundleConst.HOMEMODEL_LARGE_POSITION_KEY, 0)

            val largeImageURL = data?.get(position)?.largeImageURL

            loadLargeImage(largeImageURL)

        }
    }

    /**
     * 加载图片
     */
    private fun loadLargeImage(largeImageURL: String?) {
        loadImage(this, largeImageURL, null, object : DrawableImageViewTarget(photoView) {
            override fun onLoadStarted(placeholder: Drawable?) {
                super.onLoadStarted(placeholder)
                showLoadingDialog(this@ImageCropActivity)
            }

            override fun onResourceReady(
                resource: Drawable,
                transition: Transition<in Drawable>?
            ) {
                super.onResourceReady(resource, transition)
                dismissLoadingDialog()
                setBackground(ConvertUtils.drawable2Bitmap(resource))
            }

            override fun onLoadFailed(errorDrawable: Drawable?) {
                super.onLoadFailed(errorDrawable)
                dismissLoadingDialog()
                showMessage(getString(R.string.imagemodel_image_load_error_tip))
            }
        })
    }

    /**
     * 设置高斯模糊的背景
     *
     * @param bitmap 原图
     */
    private fun setBackground(bitmap: Bitmap) {
        //放大并模糊
        val bg = setBackgroue(bitmap, 20, 5)
        //设置给背景
        bgView?.setImageBitmap(bg)
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

}