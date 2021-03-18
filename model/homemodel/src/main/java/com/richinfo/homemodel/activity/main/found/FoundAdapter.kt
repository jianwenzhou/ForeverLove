package com.richinfo.homemodel.activity.main.found

import android.widget.ImageView
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jareven.basemodel.utils.GlideUtils.loadImage
import com.jareven.basemodel.utils.GradientDrawableUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.CaiPuDatas

/**
 * @ClassName WorldAdapter
 * @Author zjw
 * @Date 2021/3/17 23:08
 * 简介：
 */
class FoundAdapter(layoutResId: Int, data: List<CaiPuDatas>?) :
    BaseQuickAdapter<CaiPuDatas?, BaseViewHolder?>(layoutResId, data) {

    override fun convert(p0: BaseViewHolder, p1: CaiPuDatas?) {

        val view = p0.getView<ImageView>(R.id.found_item_cover_iv)

        val gradientDrawable = GradientDrawableUtils.createRandomDrawable()


        p0.setText(R.id.found_item_name_tv, p1?.cpName)

        //加载原图
        loadImage(mContext, p1?.largeImg, gradientDrawable, view)

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