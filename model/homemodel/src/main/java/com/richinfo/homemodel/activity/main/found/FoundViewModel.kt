package com.richinfo.homemodel.activity.main.found

import androidx.lifecycle.ViewModel
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import com.richinfo.httpmodel.api.manager.ApiFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @ClassName FoundViewModel
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/5 22:51
 * @Version 1.0
 * 简介：发现model
 */
class FoundViewModel : ViewModel() {

    /**
     * 获取菜谱集合
     *
     *  cpName	    STRING	可选	菜谱名称
     *  id	        STRING	可选	菜谱标识,查询具体某个菜谱信息.当传递该参数的时候,type参数将无效
     *  maxResults	INT	    可选	每次请求返回的最大数据集,取值范围1-50之间
     *  page	    INT	    可选	分页的当前页数
     *  type	    STRING	必选	菜谱的分类
     *
     */
    fun getRecipeList(
        cpName: String,
        id: String,
        maxResults: Int,
        page: Int,
        type: String
    ): Observable<AliRecipeEntity>? {
        return ApiFactory.getRecipeData()
            .getRecipeList(cpName, id, maxResults, page, type)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }

}