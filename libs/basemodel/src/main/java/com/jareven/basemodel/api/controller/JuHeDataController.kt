package com.jareven.basemodel.api.controller

import com.jareven.basemodel.api.entity.JokeEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @ClassName JuHeDataController
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/12 13:41
 * @Version 1.0
 * 简介：聚合数据的相关接口
 */
interface JuHeDataController {

    /**
     * 获取笑话数据
     */
    @GET("/joke/content/list.php")
    fun getJokeList(@Query("sort") sort: String, @Query("page") page: Int,
                             @Query("pagesize") pagesize: Int, @Query("time") time: String
                             , @Query("key") key: String): Observable<JokeEntity>

}