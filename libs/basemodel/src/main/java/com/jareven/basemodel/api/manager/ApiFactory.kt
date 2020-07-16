package com.jareven.basemodel.api.manager

import com.jareven.basemodel.api.controller.JuHeDataController

/**
 * @ClassName ApiFactory
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 23:53
 * @Version 1.0
 * 简介：
 */
object ApiFactory {

    private var api: JuHeDataController? = null

    fun getJuHeData(): JuHeDataController {
        return api ?: synchronized(ApiFactory::class) {
            api ?: RetrofitManager.getInstence().create(JuHeDataController::class.java)
        }
    }

}