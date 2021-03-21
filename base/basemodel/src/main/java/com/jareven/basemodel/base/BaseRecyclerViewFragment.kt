package com.jareven.basemodel.base

import android.view.View
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.jareven.basemodel.R

/**
 * @ClassName BaseRecyclerViewFragment
 * @Author zjw
 * @Date 2021/3/21 19:42
 * 简介：封装RecyclerView和SwipeRefreshLayout的fragment
 */
abstract class BaseRecyclerViewFragment : BaseLazyFragment() {

    private lateinit var toolbarContainer: ConstraintLayout
    protected lateinit var recyclerView: RecyclerView
    protected lateinit var refreshLayout: SwipeRefreshLayout

    override fun getLayoutID(): Int {
        return R.layout.basemodel_fragment_list_layout
    }

    override fun initView(view: View?) {
        view?.let {
            toolbarContainer = it.findViewById(R.id.basemodel_toolbar_container)!!
            recyclerView = it.findViewById(R.id.basemodel_recycler_view)!!
            refreshLayout = it.findViewById(R.id.basemodel_swipe_refresh_view)!!
        }
        initRefreshView()
        initToolBar()
    }

    private fun initRefreshView() {
        refreshLayout.setColorSchemeResources(
            R.color.red_700,
            R.color.amber_700,
            R.color.blue_700
        )
        refreshLayout.setProgressViewOffset(true, -100, 100)
    }

    private fun initToolBar() {
        toolbarContainer.addView(createToolBar())
    }

    /**
     * 子类添加Toolbar控件
     */
    abstract fun createToolBar(): View?


}