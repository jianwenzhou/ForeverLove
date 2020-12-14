package com.jareven.foreverlove

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.RouterPathConst

class OpenActivity : BaseActivity() {

    override fun getLayoutID(): Int {
        return R.layout.app_activity_open
    }

    override fun initData() {
        routerJump(RouterPathConst.ROUTER_HOMEMODEL_SPLASH_ACTIVITY)
        finish()
    }

}