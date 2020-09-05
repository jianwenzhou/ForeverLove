package com.jareven.foreverlove.activity.splash

import android.Manifest
import android.graphics.Color
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModelProvider
import com.blankj.utilcode.util.BarUtils
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.api.callback.DataCallback
import com.jareven.basemodel.api.entity.WeatherEntity
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.util.FToastUtil
import com.jareven.basemodel.util.LocationUtils
import com.jareven.foreverlove.R
import com.jareven.foreverlove.activity.main.MainActivity
import com.yanzhenjie.permission.AndPermission
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * 简介：闪屏页面
 */
class SplashActivity : BaseActivity() {

    lateinit var viewModel: SplashVIewModel

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        splash_content_tv.typeface = ResourcesCompat.getFont(this, R.font.alibaba)
        splash_skip_btn.setOnClickListener {jumpToMain()}
    }

    override fun initData() {
        viewModel = ViewModelProvider(this).get(SplashVIewModel::class.java)
        requestPermission()
    }

    override fun initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, true)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
    }

    /**
     * 定位权限申请
     */
    private fun requestPermission() {
        AndPermission.with(this)
            .runtime()
            .permission(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION
            )
            .onGranted {
                loadWeather()
            }
            .onDenied {
                loadWeather()
                FToastUtil.showShort(getString(R.string.app_location_permission_tip))
            }
            .start()
    }

    /**
     * 从网络获取天气信息
     */
    private fun loadWeather() {
        //获取位置信息
        val locations = LocationUtils.getLocations(this)
        //获取天气信息,裁剪掉 市 字
        val city = locations.substring(0, locations.length - 1)
        viewModel.getSimpleWeather(city, callback)
    }

    /**
     * 数据回调
     */
    private val callback = object : DataCallback<WeatherEntity>() {
        override fun onSuccess(t: WeatherEntity) {
            setWeatherText(t.result.realtime.toString())
        }

        override fun onError(msg: String, code: Int) {
            LogUtils.e(msg)
        }
    }


    fun setWeatherText(text: CharSequence) {
        splash_content_tv.text = text
    }


    /**
     * 跳转
     */
    private fun jumpToMain() {
        MainActivity.actionStart(this)
        finish()
    }
}