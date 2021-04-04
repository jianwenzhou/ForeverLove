package com.richinfo.homemodel.activity.main.found

import android.widget.ImageView
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.module.LoadMoreModule
import com.chad.library.adapter.base.viewholder.BaseViewHolder
import com.jareven.basemodel.utils.GlideUtils.loadImage
import com.jareven.basemodel.utils.GradientDrawableUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Step

/**
 * @ClassName WorldAdapter
 * @Author zjw
 * @Date 2021/3/17 23:08
 * 简介：菜谱详情adapter
 */
class FoundDetailsAdapter(layoutResId: Int, data: MutableList<Step>) :
    BaseQuickAdapter<Step, BaseViewHolder>(layoutResId, data), LoadMoreModule {


    override fun convert(holder: BaseViewHolder, item: Step) {

        val orderNum = item.orderNum
        val content = item.content
        holder.setText(R.id.found_details_item_name_tv, "$orderNum: $content")

        val view = holder.getView<ImageView>(R.id.found_details_item_cover_iv)
        val gradientDrawable = GradientDrawableUtils.createRandomDrawable()
        loadImage(context, item.imgUrl, gradientDrawable, view)

    }

}