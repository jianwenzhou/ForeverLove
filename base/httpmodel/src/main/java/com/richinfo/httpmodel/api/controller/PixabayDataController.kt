package com.richinfo.httpmodel.api.controller

import com.richinfo.httpmodel.api.entity.ImageEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ClassName PixabayDataController
 * @Author zjw
 * @Date 2020/12/12 17:45
 * 简介：获取https://pixabay.com/免费图片数据
 */
interface PixabayDataController {

    /**
     * 免费图片数据
     */
    @GET("https://pixabay.com/api/")
    fun getImageList(
        @Query("key") key: String,
        @Query("page") page: Int,
        @Query("per_page") per_page: Int,
        @Query("orientation") orientation: String,
        @Query("lang") lang: String,
        @Query("q") q: String
    ): Observable<ImageEntity>

}