package com.richinfo.homemodel.activity.main.world

import com.jareven.basemodel.mvp.IView

/**
 * @ClassName WorldView
 * @Author zjw
 * @Date 2020/12/13 15:42
 * 简介：View层
 */
interface CommonView<T> : IView {
    fun noMoreData()
    fun setMoreData(list: T)
    fun loadData(pullToRefresh: Boolean)
    fun setData(list: T)
    fun setEmptyOrErrorView(isEmpty: Boolean)
    fun loadMoreComplete()
}