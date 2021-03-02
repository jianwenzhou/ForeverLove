package com.richinfo.homemodel.activity.main.world

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jareven.basemodel.mvp.BasePresenter
import com.richinfo.httpmodel.api.entity.ImageEntity
import com.richinfo.httpmodel.api.manager.CallBackWrapper
import io.reactivex.disposables.Disposable

/**
 * @ClassName WorldPresenter
 * @Author zjw
 * @Date 2020/12/13 15:51
 * 简介：
 */
class WorldPresenter(owner: Fragment, private var view: WorldView<ImageEntity>) : BasePresenter() {

    private var viewModel: WorldViewModel = ViewModelProvider(owner).get(WorldViewModel::class.java)

    //页码
    private var page = 1

    //每页展示的数据
    private var pageCount = 20


    private fun reset() {
        page = 1
        pageCount = 20
    }

    /**
     * 加载数据
     * isPull true:下拉刷新或者首次加载
     */
    fun loadData(isPull: Boolean, key: String) {
        if (isPull) reset()

        viewModel.getImageList(page, pageCount, key)
            ?.subscribe(object : CallBackWrapper<ImageEntity>() {

                override fun onBegin(d: Disposable) {
                    view.showLoading(isPull)
                }

                override fun onSuccess(t: ImageEntity) {
                    if (t.hits.isEmpty()) {
                        onDataFail(true, "数据返回为空")
                        return
                    }

                    if (page == 1) {
                        //下拉刷新或者首次加载
                        view.setData(t)
                        view.showContent()
                    } else {
                        view.setMoreData(t)
                        view.loadMoreComplete()
                    }

                    val totalHits = t.totalHits
                    pageCount += 20
                    if (totalHits > pageCount) {
                        //还有更多数据
                        page++
                    } else {
                        view.noMoreData()
                    }

                }

                override fun onError(msg: String, code: Int) {
                    onDataFail(false, msg)
                }
            }
            )
    }

    private fun onDataFail(isEmpty: Boolean, msg: String) {
        if (page == 1) {
            view.setEmptyOrErrorView(isEmpty)
        } else {
            view.noMoreData()
        }
        view.showMessage(msg)
    }


}