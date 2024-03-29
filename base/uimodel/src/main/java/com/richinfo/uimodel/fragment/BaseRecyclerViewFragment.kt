package com.richinfo.uimodel.fragment

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jareven.basemodel.base.BaseLazyFragment
import com.jareven.basemodel.dialog.LoadingView
import com.richinfo.uimodel.R

/**
 * @ClassName BaseRecyclerViewFragment
 * @Author zjw
 * @Date 2021/3/21 19:42
 * 简介：封装RecyclerView和SwipeRefreshLayout的fragment
 */
abstract class BaseRecyclerViewFragment : BaseLazyFragment(), SwipeRefreshLayout.OnRefreshListener {

    private lateinit var toolbarContainer: ConstraintLayout
    protected lateinit var recyclerView: RecyclerView
    private lateinit var refreshLayout: SwipeRefreshLayout
    private lateinit var loadingView: LoadingView


    override fun getLayoutID(): Int {
        return R.layout.uimodel_fragment_list_layout
    }

    override fun initView(view: View?) {
        view?.let {
            toolbarContainer = it.findViewById(R.id.uimodel_toolbar_container)!!
            recyclerView = it.findViewById(R.id.uimodel_recycler_view)!!
            refreshLayout = it.findViewById(R.id.uimodel_swipe_refresh_view)!!
            loadingView = it.findViewById(R.id.dialog_loading_view)!!
        }
        initRefreshView()
        initToolBar()
        initRecyclerViewListener()
    }

    private fun initRecyclerViewListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                onScrolled()
            }
        })
    }

    /**
     * RecyclerView滑动事件监听，子类可以重新
     */
    protected open fun onScrolled() {

    }

    private fun initRefreshView() {
        refreshLayout.setColorSchemeResources(
            R.color.red_700,
            R.color.amber_700,
            R.color.blue_700
        )
        refreshLayout.setProgressViewOffset(true, -100, 100)
        refreshLayout.setOnRefreshListener(this)
    }

    private fun initToolBar() {
        val toolbar = createToolBar()
        toolbar?.let { it ->
            toolbarContainer.addView(it)
        }
    }

    /**
     * 子类添加Toolbar控件
     */
    abstract fun createToolBar(): View?

    /**
     * 子类实现，下拉刷新事件
     */
    override fun onRefresh() {

    }

    /**
     * 是否展示refreshingView
     */
    protected fun isRefreshing(isRefreshing: Boolean) {
        refreshLayout.isRefreshing = isRefreshing
    }


    /**
     * 创建底部view
     */
    protected fun createFooterView(): View {
        return LayoutInflater.from(context).inflate(
            R.layout.uimodel_recycler_footer, recyclerView.parent as ViewGroup, false
        )
    }

    /**
     * 创建空数据view
     */
    protected fun createEmptyView(): View {
        return LayoutInflater.from(context).inflate(
            R.layout.uimodel_recycler_empty, recyclerView.parent as ViewGroup, false
        )
    }

    /**
     * 创建错误view
     */
    protected fun createErrorView(): View {
        return LayoutInflater.from(context).inflate(
            R.layout.uimodel_recycler_error, recyclerView.parent as ViewGroup, false
        )
    }


    /**
     * 展示加载中控件
     */
    fun showLoadingView() {
        loadingView.show()
    }

    /**
     * 隐藏加载中控件
     */
    fun dismissLoadingView() {
        loadingView.dismiss()
    }

}