package com.richinfo.homemodel.activity.main.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jareven.basemodel.base.BaseLazyFragment
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.FToastUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Hit
import com.richinfo.httpmodel.api.entity.ImageEntity


/**
 * @ClassName WorldFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：世界Fragment
 */
class WorldFragment : BaseLazyFragment(), WorldView<ImageEntity> {

    private lateinit var presenter: WorldPresenter

    private lateinit var adapter: HomeAdapter

    private var refreshLayout: SwipeRefreshLayout? = null
    private var recyclerView: RecyclerView? = null

    override fun getLayoutID(): Int {
        return R.layout.homemodel_fragment_main_world
    }

    override fun initView(view: View?) {
        recyclerView = view?.findViewById(R.id.word_fragment_rv)
        refreshLayout = view?.findViewById(R.id.base_swipe_refresh_view)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        val mutableList = ArrayList<Hit>()
        adapter = HomeAdapter(R.layout.homemodel_fragment_word_item, mutableList)
        //垂直方向的2列
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //防止item 交换位置
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

        recyclerView?.layoutManager = layoutManager
        recyclerView?.adapter = adapter

        adapter.setOnLoadMoreListener({ loadData(false) }, recyclerView)
        refreshLayout?.setOnRefreshListener { loadData(true) }
        adapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener() { baseQuickAdapter, _, i ->
                val item = baseQuickAdapter.getItem(i) as Hit
                val bundle = Bundle()
                LogUtils.d(item.webformatURL)
                bundle.putString("largeImageURL", item.largeImageURL)
                routerJump(RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY, "bundle", bundle)
            }
    }


    override fun onLazyLoadOnce() {
        presenter = WorldPresenter(this, this)
        loadData(true)
    }

    override fun noMoreData() {
        addFooterView()
        adapter.loadMoreEnd(true)
    }

    override fun setMoreData(list: ImageEntity) {
        adapter.addData(list.hits)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh)
    }

    override fun setData(list: ImageEntity) {
        adapter.setNewData(list.hits)
    }

    override fun setEmptyOrErrorView(isEmpty: Boolean) {
        showLoading(false)
        if (isEmpty) {
            adapter.setEmptyView(
                R.layout.homemodel_recycler_empty,
                recyclerView?.parent as ViewGroup
            )
        } else {
            adapter.setEmptyView(
                R.layout.homemodel_recycler_error,
                recyclerView?.parent as ViewGroup
            )
        }
    }


    override fun showLoading(isPull: Boolean) {
        refreshLayout?.isRefreshing = isPull
    }

    override fun showContent() {
        refreshLayout?.isRefreshing = false
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
            R.layout.homemodel_recycler_footer, recyclerView?.parent as ViewGroup, false
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