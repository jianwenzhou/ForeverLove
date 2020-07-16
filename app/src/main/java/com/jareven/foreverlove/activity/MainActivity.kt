package com.jareven.foreverlove.activity

import android.content.Context
import android.content.Intent
import com.blankj.utilcode.util.LogUtils
import com.jareven.basemodel.api.entity.JokeEntity
import com.jareven.basemodel.api.manager.ApiFactory
import com.jareven.basemodel.api.manager.CallBackWrapper
import com.jareven.basemodel.base.ActivityCollector
import com.jareven.basemodel.base.BaseActivity
import com.jareven.basemodel.util.FToastUtil
import com.jareven.foreverlove.R
import com.jareven.foreverlove.const.NetConst
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


open class MainActivity : BaseActivity() {

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
        return R.layout.activity_main
    }


    override fun initView() {

    }



    override fun initData() {
        val timeStampSec = System.currentTimeMillis() / 1000
        val timestamp = String.format("%010d", timeStampSec)
        ApiFactory.getJuHeData()
            .getJokeList("desc", 1, 1, timestamp, NetConst.JuHeKey)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(object : CallBackWrapper<JokeEntity>() {
                override fun onSuccess(t: JokeEntity) {
                    LogUtils.e(t)
                }

                override fun onError(msg: String, code: Int) {

                }


            })

    }

    var mStartMills: Long = 0
    override fun onBackPressed() {
        val l = System.currentTimeMillis() - mStartMills
        LogUtils.d(l)
        if (l > 1000) {
            FToastUtil.showShort(getString(R.string.app_exit_app_tip))
            mStartMills = System.currentTimeMillis()
        } else {
            ActivityCollector.finishAll()
        }

    }

}

