package com.jareven.basemodel.util

import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.*
import android.os.Bundle
import com.blankj.utilcode.util.LogUtils
import java.io.IOException
import java.util.*

/**
 * @ClassName LocationUtils
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/6 1:24
 * @Version 1.0
 * 简介：获取地址信息
 */
object LocationUtils {


    @SuppressLint("MissingPermission")
    fun getLocations(context: Context): String {
        //默认未知
        var strLocation = "未知"
        try {
            //获取系统的服务，
            val locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
            //创建一个criteria对象 根据设备自动选择定位方式
            val criteria = Criteria()
            criteria.accuracy = Criteria.ACCURACY_COARSE
            //设置不需要获取海拔方向数据
            criteria.isAltitudeRequired = false
            criteria.isBearingRequired = false
            //设置允许产生资费
            criteria.isCostAllowed = true
            //要求低耗电
            criteria.powerRequirement = Criteria.POWER_LOW
            val provider = locationManager.getBestProvider(criteria, true)

            val location = locationManager.getLastKnownLocation(provider)

            //获得设备的位置
            if (location != null) {
                // 耗时操作
                strLocation = converAddress(context, location.latitude, location.longitude)
            }

        } catch (e: Exception) {
            e.printStackTrace()
        }

        return strLocation

    }

    /**
     * LocationListern监听器
     * 参数：地理位置提供器、监听位置变化的时间间隔、位置变化的距离间隔、LocationListener监听器
     */
    private val locationListener = object : LocationListener {
        override fun onLocationChanged(location: Location) {

        }

        override fun onProviderDisabled(provider: String) {
            LogUtils.i("LocationUtils", "Provider now is disabled..")
        }

        override fun onProviderEnabled(provider: String) {
            LogUtils.i("LocationUtils", "Provider now is enabled..")
        }

        override fun onStatusChanged(provider: String, status: Int, extras: Bundle) {}
    }


    /**
     * @param latitude
     * 纬度
     * @param longitude
     * 经度
     * @return 详细位置信息 GeoCoder是基于后台backend的服务，因此这个方法不是对每台设备都适用。
     */
    private fun converAddress(context: Context, latitude: Double, longitude: Double): String {
        val mGeocoder = Geocoder(context, Locale.getDefault())
        val mStringBuilder = StringBuilder()

        try {
            val mAddresses = mGeocoder.getFromLocation(latitude, longitude, 1)
            if (mAddresses.isNotEmpty()) {
                val address = mAddresses[0]
//                mStringBuilder.append(address.countryName).append(", ").append(address.adminArea)
//                    .append(", ").append(address.locality)
                //只返回城市信息
                mStringBuilder.append(address.locality)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }

        return mStringBuilder.toString()
    }

    private fun checkPermission(context: Context, permName: permission): Boolean {
        val perm = context.checkCallingOrSelfPermission("android.permission.$permName")
        return perm == PackageManager.PERMISSION_GRANTED
    }

    private enum class permission {
        ACCESS_COARSE_LOCATION,
        ACCESS_FINE_LOCATION
    }



}