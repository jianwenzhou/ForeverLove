package com.richinfo.webmodel

import android.content.Context
import android.content.Intent
import android.os.Bundle
import com.alibaba.android.arouter.facade.annotation.Route
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.RouterPathConst

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterPathConst.ROUTER_WEBMODEL_WEB_ACTIVITY)
class WebActivity : BaseActivity() {

    override fun getLayoutID(): Int {
        return R.layout.webmodel_activity_web
    }

    override fun initView() {
        val bundle = Bundle()
        bundle.putString("name", "zhangsan")
        bundle.putInt("age", 158)
        routerJump(RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY, "bundle", bundle)
        RouterPathConst
    }

    companion object {
        fun launch(context: Context) {
            val intent = Intent(context, WebActivity::class.java)
            context.startActivity(intent)
        }
    }
}