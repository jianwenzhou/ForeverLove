package com.richinfo.homemodel.activity.main.found

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jareven.basemodel.base.BaseLazyFragment
import com.jareven.basemodel.utils.FToastUtils
import com.richinfo.homemodel.R
import com.richinfo.homemodel.activity.main.world.CommonView
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import com.richinfo.httpmodel.api.entity.CaiPuDatas
import com.richinfo.uimodel.recyclerview.SpacesItemDecoration
import kotlinx.android.synthetic.main.homemodel_fragment_main_world.*

/**
 * @ClassName FoundFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：发现Fragment
 */
class FoundFragment : BaseLazyFragment(), CommonView<AliRecipeEntity> {
    //延迟初始化
    private lateinit var presenter: FoundPresenter

    private lateinit var adapter: FoundAdapter

    override fun getLayoutID(): Int {
        return R.layout.homemodel_fragment_main_found
    }

    override fun initView(view: View?) {
        initStatusBar()
        initRecyclerView()
    }

    private fun initStatusBar() {
        setStateBarView(R.id.homemodel_status_bar_fl)
    }

    /**
     * 图片列表
     */
    private fun initRecyclerView() {
        val mutableList = ArrayList<CaiPuDatas>()
        adapter = FoundAdapter(R.layout.homemodel_fragment_found_item, mutableList)

        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        word_fragment_rv?.layoutManager = layoutManager
        word_fragment_rv?.adapter = adapter

        word_fragment_rv.addItemDecoration(SpacesItemDecoration(ConvertUtils.dp2px(40f)))

        adapter.setOnLoadMoreListener({ loadData(false) }, word_fragment_rv)
        base_swipe_refresh_view?.setOnRefreshListener { loadData(true) }
        adapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { baseQuickAdapter, v, i ->

            }
    }


    override fun onLazyLoadOnce() {
        presenter = FoundPresenter(this, this)
        loadData(true)
    }

    override fun noMoreData() {
        addFooterView()
        adapter.loadMoreEnd(true)
    }

    override fun setMoreData(list: AliRecipeEntity) {
        adapter.addData(list.showapi_res_body.datas)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh, "", "", "土豆")
    }

    override fun setData(list: AliRecipeEntity) {
        adapter.setNewData(list.showapi_res_body.datas)
    }

    override fun setEmptyOrErrorView(isEmpty: Boolean) {
        showLoading(false)
        if (isEmpty) {
            adapter.setEmptyView(
                R.layout.homemodel_recycler_empty,
                word_fragment_rv?.parent as ViewGroup
            )
        } else {
            adapter.setEmptyView(
                R.layout.homemodel_recycler_error,
                word_fragment_rv?.parent as ViewGroup
            )
        }
    }


    override fun showLoading(isPull: Boolean) {
        base_swipe_refresh_view?.isRefreshing = isPull
    }

    override fun showContent() {
        base_swipe_refresh_view?.isRefreshing = false
        removeFooterView()
    }

    override fun showMessage(msg: String) {
        FToastUtils.showShort(msg)
    }

    override fun loadMoreComplete() {
        adapter.loadMoreComplete()
    }

    /**
     * 添加底部view,没有更多内容的时候展示
     */
    private fun addFooterView() {
        val footerLayoutCount: Int = adapter.footerLayoutCount
        if (footerLayoutCount > 0) {
            return
        }
        val footerView: View = LayoutInflater.from(context).inflate(
            R.layout.homemodel_recycler_footer, word_fragment_rv?.parent as ViewGroup, false
        )
        adapter.addFooterView(footerView)
    }

    /**
     * 删除底部view
     */
    private fun removeFooterView() {
        adapter.removeAllFooterView()
    }


}