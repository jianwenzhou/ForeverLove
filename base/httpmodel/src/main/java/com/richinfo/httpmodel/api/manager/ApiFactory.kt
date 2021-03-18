package com.richinfo.httpmodel.api.manager

import com.richinfo.httpmodel.api.controller.AliRecipeController
import com.richinfo.httpmodel.api.controller.JuHeDataController
import com.richinfo.httpmodel.api.controller.PixabayDataController

/**
 * @ClassName ApiFactory
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 23:53
 * @Version 1.0
 * 简介：
 */
object ApiFactory {

    private var juHeData: JuHeDataController? = null

    fun getJuHeData(): JuHeDataController {
        return juHeData ?: synchronized(ApiFactory::class) {
            juHeData ?: RetrofitManager.getInstence().create(JuHeDataController::class.java)
        }
    }

    private var pixabayData: PixabayDataController? = null

    fun getImageData(): PixabayDataController {
        return pixabayData ?: synchronized(ApiFactory::class) {
            pixabayData ?: RetrofitManager.getInstence().create(PixabayDataController::class.java)
        }
    }


    private var aliRecipeData: AliRecipeController? = null

    fun getRecipeData(): AliRecipeController {
        return aliRecipeData ?: synchronized(ApiFactory::class) {
            aliRecipeData ?: RetrofitManager.getInstence().create(AliRecipeController::class.java)
        }
    }

}