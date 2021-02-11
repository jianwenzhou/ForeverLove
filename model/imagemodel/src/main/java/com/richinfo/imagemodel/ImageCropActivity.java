package com.richinfo.imagemodel;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.BarUtils;
import com.blankj.utilcode.util.CacheDiskUtils;
import com.blankj.utilcode.util.ConvertUtils;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.jareven.basemodel.base.BaseActivity;
import com.jareven.basemodel.cons.RouterPathConst;
import com.richinfo.imagemodel.utils.BlurUtil;


@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
public class ImageCropActivity extends BaseActivity {

    private static String defaultUrl = "https://pixabay.com/get/57e8d34b4e57a514f1dc846096293f78133fd7ec554c704f742f72d4974acc58_640.jpg";

    @Autowired
    public Bundle bundle;
    private PhotoView photoView;
    private ImageView bgView;

    @Override
    public int getLayoutID() {
        return R.layout.imagemodel_activity_image_crop;
    }

    @Override
    public void initStatusBar() {
        //状态栏黑色
        BarUtils.setStatusBarLightMode(this, false);
        //状态栏背景透明
        BarUtils.setStatusBarColor(this, Color.TRANSPARENT);
    }

    @Override
    public void initData() {
        findView();
        setData();
    }

    private void findView() {
        bgView = findViewById(R.id.imagemodel_bg_view);
        photoView = findViewById(R.id.imagemodel_photo_view);
        initPhotoView(photoView);
    }

    private void setData() {
        String largeImageURL = bundle.getString("largeImageURL", defaultUrl);
        //读取缓存中的drawable
        Drawable drawable = CacheDiskUtils.getInstance().getDrawable(largeImageURL);
        //设置drawable给photoView
        photoView.setImageDrawable(drawable);
        doBlur(drawable);
    }

    /**
     * 放大并模糊
     *
     * @param drawable 原图
     */
    private void doBlur(Drawable drawable) {
        //放大并模糊
        Bitmap bg = BlurUtil.doBlur(ConvertUtils.drawable2Bitmap(drawable), 20, 5);
        //设置给背景
        bgView.setImageBitmap(bg);
    }


    /**
     * photoView初始化配置
     *
     * @param photoView photoView
     */
    private void initPhotoView(PhotoView photoView) {
        // 启用图片缩放功能
        photoView.enable();
        // 获取图片信息
        Info info = photoView.getInfo();
        // 从一张图片信息变化到现在的图片，用于图片点击后放大浏览，具体使用可以参照demo的使用
        photoView.animaFrom(info);
        // 从现在的图片变化到所给定的图片信息，用于图片放大后点击缩小到原来的位置，具体使用可以参照demo的使用
        photoView.animaTo(info, new Runnable() {
            @Override
            public void run() {
                //动画完成监听
            }
        });
        // 获取/设置 动画持续时间
        photoView.setAnimaDuring(300);
        // 获取/设置 最大缩放倍数
        photoView.setMaxScale(2);
        // 设置动画的插入器
        photoView.setInterpolator(new AccelerateDecelerateInterpolator());
    }


    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus) {
            hideSystemUI();
        }
    }

    private void hideSystemUI() {
        // Enables regular immersive mode.
        // For "lean back" mode, remove SYSTEM_UI_FLAG_IMMERSIVE.
        // Or for "sticky immersive," replace it with SYSTEM_UI_FLAG_IMMERSIVE_STICKY
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_IMMERSIVE
                        // Set the content to appear under the system bars so that the
                        // content doesn't resize when the system bars hide and show.
                        | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                        // Hide the nav bar and status bar
                        | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                /*| View.SYSTEM_UI_FLAG_FULLSCREEN*/);
    }

    // Shows the system bars by removing all the flags
    // except for the ones that make the content appear under the system bars.
    private void showSystemUI() {
        View decorView = getWindow().getDecorView();
        decorView.setSystemUiVisibility(
                View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                        | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                        | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
    }


}