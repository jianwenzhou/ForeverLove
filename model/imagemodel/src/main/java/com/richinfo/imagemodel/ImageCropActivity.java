package com.richinfo.imagemodel;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.blankj.utilcode.util.ImageUtils;
import com.bm.library.Info;
import com.bm.library.PhotoView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.jareven.basemodel.base.BaseActivity;
import com.jareven.basemodel.cons.RouterPathConst;
import com.zackratos.ultimatebarx.library.UltimateBarX;

@Route(path = RouterPathConst.ROUTER_IMAGEMODEL_IMAGE_ACTIVITY)
public class ImageCropActivity extends BaseActivity {

    private static String defaultUrl = "https://pixabay.com/get/57e8d34b4e57a514f1dc846096293f78133fd7ec554c704f742f72d4974acc58_640.jpg";

    @Autowired
    public Bundle bundle;

    @Override
    public int getLayoutID() {
        return R.layout.imagemodel_activity_image_crop;
    }

    @Override
    public void initStatusBar() {
        UltimateBarX.with(this)
                .transparent()
                .light(false)
                .applyStatusBar();
    }

    @Override
    public void initData() {
        String largeImageURL = bundle.getString("largeImageURL", defaultUrl);
        ImageView rootView = findViewById(R.id.imagemodel_bg_view);
        PhotoView photoView = findViewById(R.id.imagemodel_photo_view);

        initPhotoView(photoView);

        loadImage(largeImageURL, photoView, rootView);

    }

    private void loadImage(String largeImageURL, final PhotoView photoView, final ImageView rootView) {
        Glide.with(this)
                .load(largeImageURL)
                .into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        Bitmap bitmap = ImageUtils.drawable2Bitmap(resource);
                        Bitmap render = ImageUtils.fastBlur(bitmap, 1, 25);
                        rootView.setImageBitmap(render);
                        photoView.setImageDrawable(resource);
                    }
                });
    }

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
}