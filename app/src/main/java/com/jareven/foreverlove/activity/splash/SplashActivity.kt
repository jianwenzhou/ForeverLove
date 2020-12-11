package com.jareven.foreverlove.activity.splash

import android.Manifest
import android.animation.ObjectAnimator
import android.graphics.Color
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.launcher.ARouter
import com.blankj.utilcode.util.BarUtils
import com.jareven.basemodel.api.entity.WeatherEntity
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.callback.PermissionCallback
import com.jareven.basemodel.util.FToastUtil
import com.jareven.basemodel.util.LocationUtils
import com.jareven.foreverlove.R
import com.richinfo.webmodel.WebActivity
import kotlinx.android.synthetic.main.activity_splash.*


/**
 * 简介：闪屏页面
 */
class SplashActivity : BaseActivity() {

    private lateinit var viewModel: SplashViewModel

    override fun getLayoutID(): Int {
        return R.layout.activity_splash
    }

    override fun initView() {
        splash_skip_tv.setOnClickListener { jumpToMain() }
    }

    override fun initData() {
        viewModel = ViewModelProvider(this).get(SplashViewModel::class.java)

        requestPermission(
            permissionsCallback,
            Manifest.permission.ACCESS_COARSE_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

    override fun initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, true)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)

        BarUtils.setNavBarVisibility(this, false)
    }


    /**
     * 权限申请回调
     */
    private val permissionsCallback = object : PermissionCallback() {
        override fun onGranted() {
            loadWeather()
        }

        override fun onDenied() {
            loadWeather()
            FToastUtil.showShort(getString(R.string.app_location_permission_tip))
        }

    }


    /**
     * 从网络获取天气信息
     */
    private fun loadWeather() {
        //获取位置信息
        val locations = LocationUtils.getLocations(this)
        //获取天气信息,裁剪掉 市 字
        val city = locations.substring(0, locations.length - 1)

        viewModel.getSimpleWeather(city)

        viewModel.getWeatherLiveData().observe(this, Observer<WeatherEntity> { t: WeatherEntity? ->
            setWeatherText(t)
        })
    }


    /**
     * 展示数据
     */
    private fun setWeatherText(weather: WeatherEntity?) {
        weather?.result?.let {
            val weatherText: String =
                it.city + "\n" + it.future[0].date + "\n" + it.future[0].weather + "\n" +
                        it.future[0].temperature + "\n" + "遇见你很美好"

            val alphaAnimator = ObjectAnimator.ofFloat(
                splash_content_tv,
                "alpha", 0f, 0f, 1f
            )
            alphaAnimator.duration = 2000
            splash_content_tv.text = weatherText
            alphaAnimator.start()
        }

    }


    /**
     * 跳转
     */
    private fun jumpToMain() {
        //MainActivity.actionStart(this)
        WebActivity.launch(this)

        finish()
    }
}