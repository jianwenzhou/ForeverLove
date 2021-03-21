package com.jareven.basemodel.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.DrawableImageViewTarget

/**
 * @ClassName GlideUtils
 * @Author zjw
 * @Date 2021/2/25 22:51
 * 简介：Glide封装类
 */
object GlideUtils {

    /**
     * 图片加载
     *
     * @param context   context
     * @param url       url
     * @param imageView imageView
     */
    fun loadImage(
        context: Context?,
        url: String?,
        imageView: ImageView?
    ) {
        loadImage(context, url, null, imageView)
    }

    /**
     * 图片加载
     *
     * @param context   context
     * @param url       url
     * @param drawable  占位图drawable
     * @param imageView imageView
     */
    fun loadImage(
        context: Context?,
        url: String?,
        drawable: Drawable?,
        imageView: ImageView?
    ) {
        context?.let { Glide.with(it).load(url).placeholder(drawable).into(imageView!!) }
    }

    /**
     * 图片加载
     *
     * @param context   context
     * @param url       url
     * @param res       占位图本地资源
     * @param imageView imageView
     */
    fun loadImage(
        context: Context?,
        url: String?,
        res: Int,
        imageView: ImageView?
    ) {
        context?.let { Glide.with(it).load(url).placeholder(res).into(imageView!!) }
    }


    /**
     * 图片加载回调
     *
     * @param context   context
     * @param url       url
     * @param drawable  占位图drawable
     * @param target 回调
     */
    fun loadImage(
        context: Context?,
        url: String?,
        drawable: Drawable?,
        target: DrawableImageViewTarget
    ) {
        context?.let { Glide.with(it).load(url).placeholder(drawable).into(target) }
    }
}