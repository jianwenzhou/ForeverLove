package com.richinfo.homemodel.activity.main.found

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.blankj.utilcode.util.ConvertUtils
import com.chad.library.adapter.base.BaseQuickAdapter
import com.jareven.basemodel.cons.BundleConst
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.utils.FToastUtils
import com.richinfo.homemodel.R
import com.richinfo.homemodel.activity.main.world.CommonView
import com.richinfo.httpmodel.api.entity.AliRecipeEntity
import com.richinfo.httpmodel.api.entity.CaiPuDatas
import com.richinfo.uimodel.dialog.DialogManager
import com.richinfo.uimodel.fragment.BaseRecyclerViewFragment
import com.richinfo.uimodel.recyclerview.SpacesItemDecoration
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

    override fun initView(view: View?) {
        super.initView(view)
        initRecyclerView()
        initStatusBar()
    }

    override fun createToolBar(): View? {
        return View.inflate(context, R.layout.homemodel_fragment_main_found, null)
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

        recyclerView.layoutManager = layoutManager
        recyclerView.adapter = adapter

        recyclerView.addItemDecoration(SpacesItemDecoration(ConvertUtils.dp2px(40f)))

        adapter.setOnLoadMoreListener({ loadData(false) }, recyclerView)

        adapter.onItemClickListener =
            BaseQuickAdapter.OnItemClickListener { baseQuickAdapter, v, i ->
                val item = baseQuickAdapter.getItem(i) as CaiPuDatas

                val activityOptionsCompat: ActivityOptionsCompat =
                    ActivityOptionsCompat.makeScaleUpAnimation(
                        v,
                        (v.x.roundToInt() + v.width) / 2,
                        (v.y.roundToInt() + v.height) / 2,
                        v.width,
                        v.height
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

    override fun onRefresh() {
        loadData(true)
    }

    override fun lazyInit() {
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
            adapter.emptyView = createEmptyView()
        } else {
            adapter.emptyView = createErrorView()
        }
    }


    override fun showLoading(isPull: Boolean) {
        if (firstUpdate) {
            activity?.let { DialogManager.showLoadingDialog(it) }
        } else {
            isRefreshing(isPull)
        }
    }

    override fun showContent() {
        if (firstUpdate) {
            DialogManager.dismissLoadingDialog()
            firstUpdate = false
        } else {
            isRefreshing(false)
        }
        removeFooterView()
    }

    override fun showMessage(msg: String) {
        FToastUtils.showShort(msg)
        firstUpdate = false
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
        adapter.addFooterView(createFooterView())
    }

    /**
     * 删除底部view
     */
    private fun removeFooterView() {
        adapter.removeAllFooterView()
    }


}