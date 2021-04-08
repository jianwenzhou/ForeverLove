package com.richinfo.homemodel.activity.main.found

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.blankj.utilcode.util.LogUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.richinfo.homemodel.R
import com.richinfo.homemodel.activity.main.world.CommonView
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import com.richinfo.httpmodel.api.entity.CaiPuDatas
import com.richinfo.uimodel.fragment.BaseRecyclerViewFragment
import com.richinfo.uimodel.recyclerview.SpacesItemDecoration
import kotlinx.android.synthetic.main.homemodel_fragment_main_toolbar.*
import kotlin.math.roundToInt

/**
 * @ClassName FoundFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：发现Fragment
 */
class FoundFragment : BaseRecyclerViewFragment(), CommonView<AliRecipeEntity> {
    //延迟初始化
    private lateinit var presenter: FoundPresenter

    private lateinit var adapter: FoundAdapter

    private var firstUpdate = true

    private var searchKey: String = "牛肉"

    override fun initView(view: View?) {
        super.initView(view)
        initStatusBar()
        initRecyclerView()
        initSearchView()
    }

    override fun createToolBar(): View? {
        return View.inflate(context, R.layout.homemodel_fragment_main_toolbar, null)
    }

    private fun initStatusBar() {
        setStateBarView(R.id.homemodel_status_bar_fl)
    }

    /**
     * 搜索按钮
     */
    private fun initSearchView() {
        homemodel_search_view?.queryHint = getString(R.string.homemodel_found_search_hint)
        homemodel_search_view?.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener,
            SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    searchKey = query
                    loadData(true)
                    clearFocus()
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
    @SuppressLint("ClickableViewAccessibility")
    private fun initRecyclerView() {
        val mutableList = ArrayList<CaiPuDatas>()
        adapter = FoundAdapter(R.layout.homemodel_fragment_found_item, mutableList)

        val layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        adapter.setAnimationWithDefault(BaseQuickAdapter.AnimationType.AlphaIn)

        val hashMap: HashMap<String, Int> = HashMap()
        hashMap[SpacesItemDecoration.LEFT] = ConvertUtils.dp2px(40f)
        hashMap[SpacesItemDecoration.TOP] = ConvertUtils.dp2px(10f)
        hashMap[SpacesItemDecoration.RIGHT] = ConvertUtils.dp2px(40f)
        hashMap[SpacesItemDecoration.BOTTOM] = ConvertUtils.dp2px(10f)
        recyclerView.addItemDecoration(
            SpacesItemDecoration(
                hashMap
            )
        )

        adapter.loadMoreModule.setOnLoadMoreListener { loadData(false) }

        adapter.setOnItemClickListener { adapter, view, position ->

            clearFocus()

            val item = adapter.getItem(position) as CaiPuDatas

            val activityOptionsCompat: ActivityOptionsCompat =
                ActivityOptionsCompat.makeScaleUpAnimation(
                    view,
                    (view.x.roundToInt()),
                    (view.y.roundToInt()),
                    view.width,
                    view.height
                )

            val bundle = Bundle()
            bundle.putParcelable(BundleConst.HOMEMODEL_CAIPU_KEY, item)

            activity?.let {
                routerJump(
                    RouterPathConst.ROUTER_FOUND_DETAILS_ACTIVITY,
                    BundleConst.BUNDLE_KEY,
                    bundle,
                    activityOptionsCompat,
                    it
                )
            }
        }

    }

    /**
     * 取消焦点，第一次进入后强制取消焦点，则不会弹起键盘
     */
    private fun clearFocus() {
        homemodel_search_view?.clearFocus()
    }

    override fun onScrolled() {
        clearFocus()
    }

    override fun onRefresh() {
        loadData(true)
    }

    override fun lazyInit() {
        presenter = FoundPresenter(this, this)
        loadData(true)
    }

    override fun noMoreData() {
        addFooterView()
        adapter.loadMoreModule.loadMoreEnd(true)
    }

    override fun setMoreData(list: AliRecipeEntity) {
        adapter.addData(list.showapi_res_body.datas)
    }

    override fun loadData(pullToRefresh: Boolean) {
        presenter.loadData(pullToRefresh, "", "", searchKey)
    }

    override fun setData(list: AliRecipeEntity) {
        adapter.setNewInstance(list.showapi_res_body.datas)
    }

    override fun setEmptyOrErrorView(isEmpty: Boolean) {
        showContent()
        if (isEmpty) {
            adapter.setEmptyView(createEmptyView())
        } else {
            adapter.setEmptyView(createErrorView())
        }
    }


    override fun showLoading(isPull: Boolean) {
        if (firstUpdate) {
            showLoadingView()
        } else {
            isRefreshing(isPull)
        }
    }

    override fun showContent() {
        if (firstUpdate) {
            dismissLoadingView()
            firstUpdate = false
        } else {
            isRefreshing(false)
        }
        removeFooterView()
    }

    override fun showMessage(msg: String) {
        toast(msg)
    }

    override fun loadMoreComplete() {
        adapter.loadMoreModule.loadMoreComplete()
    }

    /**
     * 添加底部view,没有更多内容的时候展示
     */
    private fun addFooterView() {
        val footerLayoutCount: Int = adapter.footerLayoutCount
        if (footerLayoutCount > 0) {
            return
        }
        adapter.addFooterView(createFooterView())
    }

    /**
     * 删除底部view
     */
    private fun removeFooterView() {
        adapter.removeAllFooterView()
    }


}