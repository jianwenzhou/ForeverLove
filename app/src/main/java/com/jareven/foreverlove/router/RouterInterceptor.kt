package com.jareven.foreverlove.router

import android.content.Context
import com.alibaba.android.arouter.facade.Postcard
import com.alibaba.android.arouter.facade.annotation.Interceptor
import com.alibaba.android.arouter.facade.callback.InterceptorCallback
import com.alibaba.android.arouter.facade.template.IInterceptor
import com.blankj.utilcode.util.LogUtils

/**
 * @ClassName RouterInterceptor
 * @Author zjw
 * @Date 2020/12/11 10:32
 * 简介：路由拦截器，一般作为登录判断，所以防止app模块
 */
@Interceptor(priority = 1, name = "拦截器")
internal class RouterInterceptor : IInterceptor {
    override fun process(postcard: Postcard, callback: InterceptorCallback) {
        LogUtils.d("拦截器测试")

        // 处理完成，交还控制权
        callback.onContinue(postcard)

        // 觉得有问题，中断路由流程
        // callback.onInterrupt(new RuntimeException("我觉得有点异常"));

        // 以上两种至少需要调用其中一种，否则不会继续路由
    }

    override fun init(context: Context) {
        // 拦截器的初始化，会在sdk初始化的时候调用该方法，仅会调用一次
    }
}