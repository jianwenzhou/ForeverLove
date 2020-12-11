package com.jareven.basemodel.base

import android.content.Context
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.annotation.GlideModule
import com.bumptech.glide.module.AppGlideModule

/**
 * @ClassName GlideManager
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/17 21:38
 * @Version 1.0
 * 简介：
 */
@GlideModule
class GlideManager : AppGlideModule(){

    override fun applyOptions(context: Context, builder: GlideBuilder) {
        super.applyOptions(context, builder)
    }
}