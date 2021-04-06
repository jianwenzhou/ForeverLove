package com.richinfo.homemodel.activity.main

import android.content.Context
import android.content.Intent
import android.graphics.Color
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.alibaba.android.arouter.facade.annotation.Route
import com.blankj.utilcode.util.BarUtils
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.cons.RouterPathConst
import com.jareven.basemodel.manager.ActivityCollector
import com.jareven.basemodel.utils.FToastUtils
import com.richinfo.homemodel.R
import com.richinfo.homemodel.activity.main.found.FoundFragment
import com.richinfo.homemodel.activity.main.home.HomeFragment
import com.richinfo.homemodel.activity.main.live.LiveFragment
import com.richinfo.homemodel.activity.main.model.MainViewModel
import com.richinfo.homemodel.activity.main.world.WorldFragment
import com.richinfo.homemodel.adapter.MainViewPager
import kotlinx.android.synthetic.main.homemodel_activity_main.*


/**
 * 首页
 */
@Route(path = RouterPathConst.ROUTER_HOMEMODEL_HOME_ACTIVITY)
open class MainActivity : BaseActivity() {

    lateinit var viewModel: MainViewModel

    //判断是否退出APP时间戳
    private var mStartMills: Long = 0

    //companion object 静态方法
    companion object {
        fun actionStart(context: Context) {
            //apply with run 都是标准函数,简化代码,创造一个上下文的环境.
            val intent = Intent(context, MainActivity::class.java).apply {
                putExtra("", "")
                putExtra("", "")
            }
            context.startActivity(intent)
        }
    }


    override fun getLayoutID(): Int {
        return R.layout.homemodel_activity_main
    }

    override fun initStatusBar() {
        setStatusBar(true)
    }

    private fun setStatusBar(isLightMode: Boolean) {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, isLightMode)
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT)
    }

    override fun initView() {
        //ViewPager2设置
        VMainViewPager.adapter = MainViewPager(this, createFragments())

        VMainViewPager.isUserInputEnabled = false
        //底部导航栏和ViewPager2绑定
        VMainBottomBar.setupWithViewPager2(VMainViewPager)
        //viewPager切换监听
       /* VMainViewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                super.onPageSelected(position)
                if (position == 2) {
                    //状态栏白色
                    setStatusBar(false)
                } else {
                    //状态栏黑色
                    setStatusBar(true)
                }
            }
        })*/
    }

    override fun initData() {
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }


    /**
     * 创建Fragment集合，供ViewPager展示
     */
    private fun createFragments(): List<Fragment> {
        val data: MutableList<Fragment> = ArrayList()
        data.add(HomeFragment())
        data.add(LiveFragment())
        data.add(WorldFragment())
        data.add(FoundFragment())
        return data
    }


    /**
     * 点击返回键
     */
    override fun onBackPressed() {
        val interval = System.currentTimeMillis() - mStartMills
        if (interval > 1500) {
            FToastUtils.showShort(getString(R.string.homemodel_exit_app_tip))
            mStartMills = System.currentTimeMillis()
        } else {
            //退出到后台
            FToastUtils.cancel()
            //moveTaskToBack(isFinishing)
            //完全退出APP，杀死进程
            ActivityCollector.finishAll()
        }

    }


}

