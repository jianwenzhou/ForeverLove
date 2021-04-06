package com.richinfo.homemodel.activity.main.found

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.jareven.basemodel.mvp.BasePresenter
import com.richinfo.homemodel.activity.main.world.CommonView
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import com.richinfo.httpmodel.api.manager.CallBackWrapper
import io.reactivex.disposables.Disposable

/**
 * @ClassName FoundPresenter
 * @Description TODO
 * @Author zjw
 * @Date 2020/9/5 18:58
 * @Version 1.0
 * 简介：发现页面的P层
 */
class FoundPresenter(owner: Fragment, private var view: CommonView<AliRecipeEntity>) :
    BasePresenter() {
    private var viewModel: FoundViewModel = ViewModelProvider(owner).get(FoundViewModel::class.java)


    //页码
    private var page = 1

    //每页展示的数据
    private var maxResults = 10


    private fun reset() {
        page = 1
        maxResults = 10
    }

    /**
     * 加载数据
     * isPull true:下拉刷新或者首次加载
     */
    fun loadData(isPull: Boolean, cpName: String, id: String, type: String) {
        if (isPull) reset()

        viewModel.getRecipeList(cpName, id, maxResults, page, type)
            ?.subscribe(object : CallBackWrapper<AliRecipeEntity>() {

                override fun onBegin(d: Disposable) {
                    view.showLoading(isPull)
                }

                override fun onSuccess(t: AliRecipeEntity) {
                    if (t.showapi_res_body.datas.isEmpty()) {
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

                    val totalHits = t.showapi_res_body.allPage
                    page++
                    if (totalHits > page) {
                        //还有更多数据
                        page++
                    } else {
                        view.noMoreData()
                    }
                }

                override fun onError(msg: String, code: Int) {
                    if (code == 0) {
                        onDataFail(false, "没有搜索到内容..")
                    } else {
                        onDataFail(false, msg)
                    }
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