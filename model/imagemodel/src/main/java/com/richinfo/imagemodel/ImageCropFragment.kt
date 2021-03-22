package com.richinfo.imagemodel

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import android.view.animation.AccelerateDecelerateInterpolator
import android.widget.ImageView
import com.blankj.utilcode.util.ConvertUtils
import com.bm.library.PhotoView
import com.bumptech.glide.request.target.DrawableImageViewTarget
import com.bumptech.glide.request.transition.Transition
import com.jareven.basemodel.base.BaseFragment
import com.jareven.basemodel.utils.BlurUtils
import com.jareven.basemodel.utils.GlideUtils
import com.richinfo.httpmodel.api.entity.Hit

/**
 * @ClassName ImageCropFragment
 * @Author zjw
 * @Date 2021/3/22 0:00
 * 简介：
 */
class ImageCropFragment : BaseFragment() {

    private var data: Hit? = null

    companion object {
        fun getInstance(data: Hit?): ImageCropFragment {
            val fragment = ImageCropFragment()
            val bundle = Bundle()
            bundle.putParcelable("data", data)
            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        data = arguments?.getParcelable("data")
    }

    override fun getLayoutID(): Int {
        return R.layout.imagemodel_viewpager_item
    }

    override fun initView(view: View?) {
        val bgView = view?.findViewById<ImageView>(R.id.imagemodel_item_bg_view)
        val photoView = view?.findViewById<ImageView>(R.id.imagemodel_item_photo_view)

        loadLargeImage(data?.largeImageURL, bgView, photoView)
    }

    /**
     * 加载图片
     */
    private fun loadLargeImage(
        largeImageURL: String?,
        bgView: ImageView?,
        photoView: ImageView?
    ) {

        GlideUtils.loadImage(
            activity,
            largeImageURL,
            null,
            object : DrawableImageViewTarget(photoView) {
                override fun onLoadStarted(placeholder: Drawable?) {
                    super.onLoadStarted(placeholder)
                    showLoadingView()
                }

                override fun onResourceReady(
                    resource: Drawable,
                    transition: Transition<in Drawable>?
                ) {
                    super.onResourceReady(resource, transition)
                    dismissLoadingView()
                    setBackground(ConvertUtils.drawable2Bitmap(resource), bgView)
                }

                override fun onLoadFailed(errorDrawable: Drawable?) {
                    super.onLoadFailed(errorDrawable)
                    dismissLoadingView()
                    toast(getString(R.string.imagemodel_image_load_error_tip))
                }
            })
    }

    /**
     * 设置高斯模糊的背景
     *
     * @param bitmap 原图
     */
    private fun setBackground(bitmap: Bitmap, bgView: ImageView?) {
        //放大并模糊
        val bg = BlurUtils.setBackgroue(bitmap, 30, 5)
        //设置给背景
        bgView?.setImageBitmap(bg)
    }

    /**
     * photoView初始化配置
     *
     * @param photoView photoView
     */
    private fun initPhotoView(photoView: PhotoView?) {
        photoView?.let {
            // 启用图片缩放功能
            it.enable()
            // 获取图片信息
            val info = it.info
            // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
            it.animaFrom(info)
            // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
            it.animaTo(info) {
                //动画完成监听
            }
            // 获取/设置 动画持续时间
            it.animaDuring = 300
            // 获取/设置 最大缩放倍数
            it.maxScale = 2f
            // 设置动画的插入器
            it.setInterpolator(AccelerateDecelerateInterpolator())
        }

    }

}