package com.richinfo.homemodel.activity.main.world

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.blankj.utilcode.util.CacheDiskUtils
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jareven.basemodel.base.BaseLazyFragment
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.FToastUtils
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.entity.Hit
import com.richinfo.httpmodel.api.entity.ImageEntity
import kotlinx.android.synthetic.main.homemodel_fragment_main_world.*


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


    private var searchKey: String = "风景"

    override fun getLayoutID(): Int {
        return R.layout.homemodel_fragment_main_world
    }

    override fun initView(view: View?) {
        initStatusBar()
        initRecyclerView()
        initSearchView()
    }

    private fun initStatusBar() {
        setStateBarView(R.id.homemodel_status_bar_fl)
    }

    /**
     * 搜索按钮
     */
    private fun initSearchView() {
        homemodel_search_view?.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchKey = query
                    loadData(true)
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                LogUtils.d("zjw newText=$newText")
                return false
            }
        })
    }

    /**
     * 图片列表
     */
    private fun initRecyclerView() {
        val mutableList = ArrayList<Hit>()
        adapter = HomeAdapter(R.layout.homemodel_fragment_word_item, mutableList)
        //垂直方向的2列
        val layoutManager =
            StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        //防止item 交换位置
        layoutManager.gapStrategy = StaggeredGridLayoutManager.GAP_HANDLING_NONE

        word_fragment_rv?.layoutManager = layoutManager
        word_fragment_rv?.adapter = adapter
        //解决滑动冲突
        word_fragment_rv?.isNestedScrollingEnabled = false

        adapter.setOnLoadMoreListener({ loadData(false) }, word_fragment_rv)
        base_swipe_refresh_view?.setOnRefreshListener { loadData(true) }
        adapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { baseQuickAdapter, v, i ->
                //item中获取imageView
                val imView = v.findViewById<ImageView>(R.id.word_item_cover_iv)
                val item = baseQuickAdapter.getItem(i) as Hit

                CacheDiskUtils.getInstance().put(item.largeImageURL, imView.drawable)

                val bundle = Bundle()
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
        presenter.loadData(pullToRefresh, searchKey)
    }

    override fun setData(list: ImageEntity) {
        adapter.setNewData(list.hits)
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