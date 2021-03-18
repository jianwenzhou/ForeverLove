package com.richinfo.homemodel.activity.main.found

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.GlideUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.CaiPuDatas
import kotlinx.android.synthetic.main.homemodel_activity_found_details.*

@Route(path = RouterPathConst.ROUTER_FOUND_DETAILS_ACTIVITY)
class FoundDetailsActivity : BaseActivity() {

    @JvmField
    @Autowired
    var bundle: Bundle? = null


    override fun getLayoutID(): Int {
        return R.layout.homemodel_activity_found_details
    }

    override fun initView() {

    }

    override fun initData() {
        val data = bundle?.getParcelable<CaiPuDatas>(BundleConst.HOMEMODEL_CAIPU_KEY)

        GlideUtils.loadImage(this, data?.largeImg, activity_found_details_cover_iv)

    }

    override fun initStatusBar() {

    }

}