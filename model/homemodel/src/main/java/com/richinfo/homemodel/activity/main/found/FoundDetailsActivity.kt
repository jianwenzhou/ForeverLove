package com.richinfo.homemodel.activity.main.found

import android.graphics.Color
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.google.android.material.appbar.AppBarLayout
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.GlideUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.CaiPuDatas
import com.richinfo.httpmodel.api.entity.Step
import com.richinfo.uimodel.recyclerview.SpacesItemDecoration
import kotlinx.android.synthetic.main.homemodel_activity_found_details.*

@Route(path = RouterPathConst.ROUTER_FOUND_DETAILS_ACTIVITY)
class FoundDetailsActivity : BaseActivity() {

    @JvmField
    @Autowired
    var bundle: Bundle? = null

    private lateinit var adapter: FoundDetailsAdapter

    override fun getLayoutID(): Int {
        return R.layout.homemodel_activity_found_details
    }

    override fun initView() {

        activity_found_details_back_iv.setOnClickListener { finish() }

        setToolbarAlpha()

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val mutableList = ArrayList<Step>()
        adapter = FoundDetailsAdapter(R.layout.homemodel_fragment_found_details_item, mutableList)

        val layoutManager =
            LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        activity_found_details_recyclerview.layoutManager = layoutManager
        activity_found_details_recyclerview.adapter = adapter

        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)


        val hashMap: HashMap<String, Int> = HashMap()
        hashMap[SpacesItemDecoration.LEFT] = ConvertUtils.dp2px(20f)
        hashMap[SpacesItemDecoration.TOP] = ConvertUtils.dp2px(10f)
        hashMap[SpacesItemDecoration.RIGHT] = ConvertUtils.dp2px(20f)
        hashMap[SpacesItemDecoration.BOTTOM] = ConvertUtils.dp2px(10f)
        activity_found_details_recyclerview.addItemDecoration(
            SpacesItemDecoration(
                hashMap
            )
        )
    }

    /**
     * 跟进滑动，设置toolBar透明度
     */
    private fun setToolbarAlpha() {
        activity_found_details_toolbar.background.alpha = 0
        //为封面减去toolbar的高度
        val alphaMaxOffset =
            ConvertUtils.dp2px(220f) - ConvertUtils.dp2px(44f) - BarUtils.getStatusBarHeight()

        val onOffsetChangedListener = AppBarLayout.OnOffsetChangedListener { _, verticalOffset ->
            if (verticalOffset > -alphaMaxOffset) {
                val i = 255 * -verticalOffset / alphaMaxOffset
                activity_found_details_toolbar.background.alpha = i
                //设置伸开的文字
                activity_found_details_ctl.title = ""
            } else {
                activity_found_details_toolbar.background.alpha = 255
                //设置收缩title以及颜色
                activity_found_details_ctl.title = data?.cpName
                activity_found_details_ctl.setCollapsedTitleTextColor(Color.WHITE)
                activity_found_details_ctl.setExpandedTitleColor(Color.WHITE)
            }
        }
        activity_found_details_abl.addOnOffsetChangedListener(onOffsetChangedListener)
    }


    private val data: CaiPuDatas?
        get() {
            return bundle?.getParcelable(BundleConst.HOMEMODEL_CAIPU_KEY)
        }

    override fun initData() {
        //设置cover
        GlideUtils.loadImage(this, data?.largeImg, activity_found_details_cover_iv)
        GlideUtils.loadImage(this, data?.smallImg, activity_found_details_user_iv)

        activity_found_details_name_tv.text = data?.cpName
        //设置recyclerView数据
        adapter.setNewInstance(data?.steps)
    }

    override fun initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, false)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)

        val statusBarHeight = BarUtils.getStatusBarHeight()
        activity_found_details_toolbar.setPadding(0, statusBarHeight, 0, 0)
        activity_found_details_toolbar.layoutParams.height =
            ConvertUtils.dp2px(44f) + statusBarHeight

    }

}