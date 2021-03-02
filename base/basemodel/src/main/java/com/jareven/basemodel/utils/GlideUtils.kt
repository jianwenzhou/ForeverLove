package com.jareven.basemodel.utils

import android.content.Context
import android.graphics.drawable.Drawable
import android.widget.ImageView
import com.bumptech.glide.Glide

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
        Glide.with(context!!).load(url).placeholder(drawable).into(imageView!!)
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
        Glide.with(context!!).load(url).placeholder(res).into(imageView!!)
    }
}