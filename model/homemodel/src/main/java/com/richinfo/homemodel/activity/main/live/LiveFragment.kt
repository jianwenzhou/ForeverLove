package com.richinfo.homemodel.activity.main.live

import android.view.View
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.base.BaseLazyFragment
import com.richinfo.homemodel.R
import com.richinfo.httpmodel.api.const.NetConst
import com.richinfo.httpmodel.api.entity.JokeEntity
import com.richinfo.httpmodel.api.manager.ApiFactory
import com.richinfo.httpmodel.api.manager.CallBackWrapper
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.homemodel_fragment_main_live.*


/**
 * @ClassName LiveFragment
 * @Description TODO
 * @Author zjw
 * @Date 2020/7/16 23:52
 * @Version 1.0
 * 简介：生活Fragment
 */
class LiveFragment : BaseLazyFragment() {
    override fun lazyInit() {
    }


    override fun getLayoutID(): Int {
        return R.layout.homemodel_fragment_main_live
    }

    override fun initView(view: View?) {

    }

    override fun initData() {
        textView.text = "明天会更好"
    }


    fun initDatas() {
        val timeStampSec = System.currentTimeMillis() / 1000
        val timestamp = String.format("%010d", timeStampSec)
        ApiFactory.getJuHeData()
            .getJokeList("desc", 1, 1, timestamp, NetConst.JokeJuHeKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallBackWrapper<JokeEntity>() {
                override fun onSuccess(t: JokeEntity) {
                    LogUtils.e(t.toString())
                }

                override fun onError(msg: String, code: Int) {

                }


            })

    }

}