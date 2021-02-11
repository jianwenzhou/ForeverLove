package com.richinfo.homemodel.activity.main.world

import androidx.lifecycle.ViewModel
import com.richinfo.httpmodel.api.const.NetConst
import com.richinfo.httpmodel.api.entity.ImageEntity
import com.richinfo.httpmodel.api.manager.ApiFactory
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * @ClassName WorldModel
 * @Author zjw
 * @Date 2020/12/12 17:51
 * 简介：
 */
class WorldViewModel : ViewModel() {

    //搜索图片相关的常量
    val orientation = "vertical"
    val lang = "zh"
    val q = "美女"

    /**
     * 下拉刷新或者首次刷新 为true
     */
    fun getImageList(page: Int, per_page: Int): Observable<ImageEntity>? {

        return ApiFactory.getImageData()
            .getImageList(NetConst.PixabayImageKey, page, per_page, orientation, lang, q)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}