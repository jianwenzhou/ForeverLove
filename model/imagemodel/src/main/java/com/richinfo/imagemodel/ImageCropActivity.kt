package com.richinfo.imagemodel

import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Autowired
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.RouterPathConst

@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
class ImageCropActivity : BaseActivity() {
    @JvmField
    @Autowired
    var bundle: Bundle? = null
    override fun getLayoutID(): Int {
        return R.layout.imagemodel_activity_image_crop
    }

    override fun initData() {
        val age = bundle!!.getInt("age")
        val name = bundle!!.getString("name")
        LogUtils.d("$name---$age")
    }
}