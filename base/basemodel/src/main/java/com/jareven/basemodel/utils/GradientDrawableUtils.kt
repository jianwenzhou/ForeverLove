package com.jareven.basemodel.utils

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import java.util.*

/**
 * @ClassName GradientDrawableUtils
 * @Author zjw
 * @Date 2021/3/2 15:28
 * 简介：shape代码生成工具类
 */
object GradientDrawableUtils {

    private var random: Random = Random()

    /**
     * 生成一个随机的矩形块
     */
    fun createRandomDrawable(): GradientDrawable {
        val gradientDrawable = GradientDrawable()
        // 形状-圆角矩形
        gradientDrawable.shape = GradientDrawable.RECTANGLE
        // 圆角
        //gradientDrawable.setCornerRadius(8)
        // 随机颜色
        val alpha = 255
        val red = 55 + random.nextInt(100)
        val green = 55 + random.nextInt(100)
        val blue = 55 + random.nextInt(100)
        gradientDrawable.setColor(Color.argb(alpha, red, green, blue))

        return gradientDrawable
    }


}