package com.richinfo.homemodel.activity.main.world

import android.graphics.Color
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.ImageView
import com.blankj.utilcode.util.ScreenUtils
import com.bumptech.glide.Glide
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Hit
import me.samlss.broccoli.Broccoli
import me.samlss.broccoli.BroccoliGradientDrawable
import me.samlss.broccoli.PlaceholderParameter


/**
 * @ClassName WorldAdapter
 * @Author zjw
 * @Date 2020/12/12 18:10
 * 简介：
 */
class HomeAdapter(layoutResId: Int, data: List<Hit>?) :
    BaseQuickAdapter<Hit?, BaseViewHolder?>(layoutResId, data) {

    private val mViewPlaceholderManager: Map<View, Broccoli> = HashMap()

    override fun convert(p0: BaseViewHolder, p1: Hit?) {

        val view = p0.getView<ImageView>(R.id.word_item_cover_iv)

        setImageSize(p1?.webformatWidth, p1?.webformatHeight, view)

        Glide.with(mContext)
            .load(p1?.webformatURL)
            .placeholder(R.mipmap.basemodel_glide_placeholder)
            .into(view)
    }

    private fun setImageSize(
        imageWidth: Int?,
        imageHeight: Int?,
        view: ImageView
    ) {

        val videoHeight = imageHeight!! * (ScreenUtils.getScreenWidth() / 2) / imageWidth!!

        //动态设置控件宽高
        val layoutParams = view.layoutParams
        layoutParams.height = videoHeight
        layoutParams.width = ScreenUtils.getScreenWidth() / 2
        view.layoutParams = layoutParams
    }

}