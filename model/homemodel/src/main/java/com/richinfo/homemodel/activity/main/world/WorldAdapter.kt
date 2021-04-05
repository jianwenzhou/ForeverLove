package com.richinfo.homemodel.activity.main.world

import android.widget.ImageView
import com.blankj.utilcode.util.ScreenUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jareven.basemodel.utils.GlideUtils.loadImage
import com.jareven.basemodel.utils.GradientDrawableUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Hit

/**
 * @ClassName WorldAdapter
 * @Author zjw
 * @Date 2021/3/17 23:08
 * 简介：
 */
class WorldAdapter(layoutResId: Int, data: MutableList<Hit>) :
    BaseQuickAdapter<Hit, BaseViewHolder>(layoutResId, data), LoadMoreModule {

    override fun convert(holder: BaseViewHolder, item: Hit) {

        val view = holder.getView<ImageView>(R.id.word_item_cover_iv)

        setImageSize(item.previewWidth, item.previewHeight, view)

        val gradientDrawable = GradientDrawableUtils.createRandomDrawable()

        //加载原图
        loadImage(context, item.webformatURL, gradientDrawable, view)
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