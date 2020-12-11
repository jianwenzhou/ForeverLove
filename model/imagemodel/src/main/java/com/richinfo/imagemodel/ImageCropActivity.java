package com.richinfo.imagemodel;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.LogUtils;
import com.jareven.basemodel.base.BaseActivity;
import com.jareven.basemodel.cons.RouterPathConst;

@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
public class ImageCropActivity extends BaseActivity {


    @Autowired
    public Bundle bundle;

    @Override
    public int getLayoutID() {
        return R.layout.imagemodel_activity_image_crop;
    }

    @Override
    public void initData() {
        int age = bundle.getInt("age");
        String name = bundle.getString("name");
        LogUtils.d(name+"---"+age);
    }
}