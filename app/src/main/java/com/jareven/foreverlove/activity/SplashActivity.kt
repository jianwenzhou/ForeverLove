package com.jareven.foreverlove.activity

import android.Manifest
import android.content.Context
import android.graphics.Color
import android.os.Handler
import androidx.core.content.res.ResourcesCompat
import com.blankj.utilcode.util.BarUtils
import com.hanks.htextview.base.AnimationListener
import com.hanks.htextview.base.HTextView
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.util.FToastUtil
import com.jareven.foreverlove.R
import com.yanzhenjie.permission.AndPermission
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * 简介：闪屏页面
 */
class SplashActivity : BaseActivity() {

    private val mHandler = Handler()

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        splashTyperTextView.setAnimationListener(SimpleAnimationListener(this))
        val typeface = ResourcesCompat.getFont(this, R.font.alibaba)
        splashTyperTextView.typeface = typeface
        splashTyperTextView.animateText(getString(R.string.splash_text))
    }

    override fun initData() {

    }

    override fun initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this,true)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this,Color.TRANSPARENT)
    }


    internal inner class SimpleAnimationListener(private val context: Context) : AnimationListener {
        override fun onAnimationEnd(hTextView: HTextView) {
            mHandler.postDelayed({ this@SplashActivity.requestPermission() }, 2000)
        }
    }

    private fun requestPermission() {
        AndPermission.with(this)
            .runtime()
            .permission(
                Manifest.permission.INTERNET
            )
            .onGranted { jumpToMain() }
            .onDenied{ FToastUtil.showShort(getString(R.string.app_net_permission_tip))}
            .start()
    }

    private fun jumpToMain() {
        MainActivity.actionStart(this)
        finish()
    }
}