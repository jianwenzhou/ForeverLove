package com.richinfo.webmodel;

import android.content.Context;
import android.content.Intent;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.jareven.basemodel.base.BaseActivity;
import com.jareven.basemodel.cons.RouterPathConst;

// 在支持路由的页面上添加注解(必选)
// 这里的路径需要注意的是至少需要有两级，/xx/xx
@Route(path = RouterPathConst.ROUTER_WEBMODEL_WEB_ACTIVITY)
public class WebActivity extends BaseActivity {

    public static void launch(Context context){
        Intent intent = new Intent(context,WebActivity.class);
        context.startActivity(intent);
    }

    @Override
    public int getLayoutID() {
        return R.layout.webmodel_activity_web;
    }

    @Override
    public void initView() {



    }
}