package com.richinfo.homemodel.activity.main.world

import android.widget.ImageView
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.jareven.basemodel.utils.GlideUtils.loadImage
import com.jareven.basemodel.utils.GradientDrawableUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Hit


/**
 * @ClassName WorldAdapter
 * @Author zjw
 * @Date 2020/12/12 18:10
 * 简介：
 */
class HomeAdapter(layoutResId: Int, data: List<Hit>?) :
    BaseQuickAdapter<Hit?, BaseViewHolder?>(layoutResId, data) {

    override fun convert(p0: BaseViewHolder, p1: Hit?) {

        val view = p0.getView<ImageView>(R.id.word_item_cover_iv)

        setImageSize(p1?.previewWidth, p1?.previewHeight, view)

        val gradientDrawable = GradientDrawableUtils.createRandomDrawable()

        //加载原图
        loadImage(mContext, p1?.webformatURL, gradientDrawable, view)

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