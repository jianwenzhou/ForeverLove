package com.richinfo.httpmodel.api.controller

import com.richinfo.httpmodel.api.const.NetConst
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Query

/**
 * @ClassName PixabayDataController
 * @Author zjw
 * @Date 2020/12/12 17:45
 * 简介：获取阿里食谱接口
 */
interface AliRecipeController {

    /**
     * 阿里食谱接口
     *
     *  cpName	    STRING	可选	菜谱名称
     *  id	        STRING	可选	菜谱标识,查询具体某个菜谱信息.当传递该参数的时候,type参数将无效
     *  maxResults	INT	    可选	每次请求返回的最大数据集,取值范围1-50之间
     *  page	    INT	    可选	分页的当前页数
     *  type	    STRING	必选	菜谱的分类
     *
     */
    @GET("https://caipu.market.alicloudapi.com/showapi_cpQuery")
    fun getRecipeList(
        @Query("cpName") cpName: String,
        @Query("id") id: String,
        @Query("maxResults") maxResults: Int,
        @Query("page") page: Int,
        @Query("type") type: String,
        @Header("Authorization") author: String = NetConst.AliRecipeKey
    ): Observable<AliRecipeEntity>

}